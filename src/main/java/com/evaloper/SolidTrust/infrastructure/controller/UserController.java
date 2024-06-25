package com.evaloper.SolidTrust.infrastructure.controller;

import com.evaloper.SolidTrust.payload.request.CreditAndDebitRequest;
import com.evaloper.SolidTrust.payload.request.EnquiryRequest;
import com.evaloper.SolidTrust.payload.request.TransferRequest;
import com.evaloper.SolidTrust.payload.response.BankResponse;
import com.evaloper.SolidTrust.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Account Management APIs")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Balance Enquiry",
            description = "Check Balance Enquiry"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"

    )
    @GetMapping("/balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request){
        return  userService.balanceEnquiry(request);
    }

    @GetMapping("/name-enquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request){
        return userService.nameEnquiry(request);
    }

    @PostMapping("/credit-account")
    public BankResponse creditAccount(@RequestBody CreditAndDebitRequest request){
        return userService.creditAccount(request);
    }

    @PostMapping("/debit-account")
    public BankResponse debitAccount(@RequestBody CreditAndDebitRequest request){
        return userService.debitAccount(request);
    }

    @PostMapping("/transfer")
    public BankResponse transfer(@RequestBody TransferRequest request){
        return userService.transfer(request);
    }
}
