package com.evaloper.SolidTrust.service;

import com.evaloper.SolidTrust.payload.request.CreditAndDebitRequest;
import com.evaloper.SolidTrust.payload.request.EnquiryRequest;
import com.evaloper.SolidTrust.payload.request.TransferRequest;
import com.evaloper.SolidTrust.payload.response.BankResponse;

public interface UserService {
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditAndDebitRequest request);

    BankResponse debitAccount(CreditAndDebitRequest request);

    BankResponse transfer(TransferRequest request);

}
