package com.evaloper.SolidTrust.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String transactionType;
    private String accountNumber;
    private BigDecimal amount;
    private String status;
}

