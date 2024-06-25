package com.evaloper.SolidTrust.service.impl;

import com.evaloper.SolidTrust.domain.entity.Transaction;
import com.evaloper.SolidTrust.payload.request.TransactionRequest;
import com.evaloper.SolidTrust.repository.TransactionRepository;
import com.evaloper.SolidTrust.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionRequest.getTransactionType())
                .accountNumber(transactionRequest.getAccountNumber())
                .amount(transactionRequest.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);

        System.out.println("Transaction saved successfully");

    }
}

