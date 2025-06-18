package com.raaflahar.maybank.controller;

import com.raaflahar.maybank.constant.ApiEndpoint;
import com.raaflahar.maybank.dto.account.AccountRequest;
import com.raaflahar.maybank.dto.account.AccountResponse;
import com.raaflahar.maybank.service.AccountService;
import com.raaflahar.maybank.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiEndpoint.Account.ACCOUNT)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable UUID id, @Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountService.updateAccount(id, request);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(ResponseUtil.success("Account deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable UUID id) {
        AccountResponse response = accountService.getAccountById(id);
        return ResponseEntity.ok(ResponseUtil.success(response));
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ResponseUtil.paged(accountService.getAllAccounts(pageable)));
    }
}
