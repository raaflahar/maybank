package com.raaflahar.maybank.service.impl;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaflahar.maybank.dto.transaction.TransactionRequest;
import com.raaflahar.maybank.dto.transaction.TransactionResponse;
import com.raaflahar.maybank.entity.Account;
import com.raaflahar.maybank.entity.Transaction;
import com.raaflahar.maybank.repository.AccountRepository;
import com.raaflahar.maybank.repository.TransactionRepository;
import com.raaflahar.maybank.service.TransactionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        BigDecimal newBalance = calculateNewBalance(account.getBalance(), request.getType(), request.getAmount());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .accountId(request.getAccountId())
                .type(request.getType())
                .amount(request.getAmount())
                .build();

        transaction = transactionRepository.save(transaction);

        return mapToResponse(transaction);
    }

    @Override
    public TransactionResponse getTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        return mapToResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable).map(this::mapToResponse);
    }

    private TransactionResponse mapToResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }

    private BigDecimal calculateNewBalance(BigDecimal currentBalance, 
                                            com.raaflahar.maybank.constant.enums.TransactionType type, 
                                            BigDecimal amount) {
        return switch (type) {
            case DEPOSIT -> currentBalance.add(amount);
            case WITHDRAWAL -> currentBalance.subtract(amount);
        };
    }
}
