package com.evaloper.SolidTrust.service;

import com.evaloper.SolidTrust.payload.response.BankResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface GeneralUserService {
    ResponseEntity<BankResponse<String>> uploadProfilePics(MultipartFile multipartFile);
}
