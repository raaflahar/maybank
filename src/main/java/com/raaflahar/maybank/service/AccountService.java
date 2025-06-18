package com.raaflahar.maybank.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raaflahar.maybank.dto.account.AccountRequest;
import com.raaflahar.maybank.dto.account.AccountResponse;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    AccountResponse updateAccount(UUID id, AccountRequest request);
    void deleteAccount(UUID id);
    AccountResponse getAccountById(UUID id);
    Page<AccountResponse> getAllAccounts(Pageable pageable);
}
