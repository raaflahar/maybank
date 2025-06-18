package com.raaflahar.maybank.controller;

import com.raaflahar.maybank.constant.ApiEndpoint;
import com.raaflahar.maybank.dto.transaction.TransactionRequest;
import com.raaflahar.maybank.dto.transaction.TransactionResponse;
import com.raaflahar.maybank.service.TransactionService;
import com.raaflahar.maybank.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiEndpoint.Transaction.TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.createTransaction(request);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable UUID id) {
        TransactionResponse response = transactionService.getTransactionById(id);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ResponseUtil.paged(transactionService.getAllTransactions(pageable)));
    }
}
