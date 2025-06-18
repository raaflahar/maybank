package com.raaflahar.maybank.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raaflahar.maybank.dto.transaction.TransactionRequest;
import com.raaflahar.maybank.dto.transaction.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request);
    TransactionResponse getTransactionById(UUID id);
    Page<TransactionResponse> getAllTransactions(Pageable pageable);
}
