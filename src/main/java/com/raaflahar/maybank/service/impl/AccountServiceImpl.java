package com.raaflahar.maybank.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raaflahar.maybank.dto.account.AccountRequest;
import com.raaflahar.maybank.dto.account.AccountResponse;
import com.raaflahar.maybank.entity.Account;
import com.raaflahar.maybank.repository.AccountRepository;
import com.raaflahar.maybank.service.AccountService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }

        Account account = Account.builder()
                .customerId(request.getCustomerId())
                .accountNumber(request.getAccountNumber())
                .balance(request.getBalance())
                .build();

        account = accountRepository.save(account);
        return mapToResponse(account);
    }

    @Override
    public AccountResponse updateAccount(UUID id, AccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        account.setAccountNumber(request.getAccountNumber());
        account.setCustomerId(request.getCustomerId());
        account.setBalance(request.getBalance());

        account = accountRepository.save(account);
        return mapToResponse(account);
    }

    @Override
    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponse getAccountById(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return mapToResponse(account);
    }

    @Override
    public Page<AccountResponse> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable).map(this::mapToResponse);
    }

    private AccountResponse mapToResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}
