package com.raaflahar.maybank.dto.account;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {

    @NotNull
    private UUID customerId;

    @NotBlank
    private String accountNumber;

    @NotNull
    private BigDecimal balance;
}
