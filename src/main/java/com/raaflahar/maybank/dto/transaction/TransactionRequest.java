package com.raaflahar.maybank.dto.transaction;

import java.math.BigDecimal;
import java.util.UUID;

import com.raaflahar.maybank.constant.enums.TransactionType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {

    @NotNull
    private UUID accountId;

    @NotNull
    private TransactionType type;

    @NotNull
    private BigDecimal amount;
}
