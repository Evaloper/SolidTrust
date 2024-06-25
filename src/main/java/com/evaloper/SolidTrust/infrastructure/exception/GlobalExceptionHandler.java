package com.evaloper.SolidTrust.infrastructure.exception;

import com.evaloper.SolidTrust.domain.entity.ErrorDetails;
import com.evaloper.SolidTrust.payload.response.BankResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailNotSendException.class)
    public ResponseEntity<BankResponse<ErrorDetails>> handleEmailNotSendException(final EmailNotSendException ex){
        ErrorDetails errorDetail = ErrorDetails.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .debugMessage("Email not sent")
                .build();


        BankResponse<ErrorDetails> response = new BankResponse<>(ex.getMessage(), errorDetail);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}


