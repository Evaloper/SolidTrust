package com.evaloper.SolidTrust.service;

import com.evaloper.SolidTrust.payload.request.TransactionRequest;

public interface TransactionService {
    void saveTransaction(TransactionRequest transactionRequest);
}
