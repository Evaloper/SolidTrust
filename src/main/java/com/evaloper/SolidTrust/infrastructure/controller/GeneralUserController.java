package com.evaloper.SolidTrust.infrastructure.controller;

import com.evaloper.SolidTrust.infrastructure.security.JwtAuthenticationFilter;
import com.evaloper.SolidTrust.infrastructure.security.JwtTokenProvider;
import com.evaloper.SolidTrust.payload.response.BankResponse;
import com.evaloper.SolidTrust.service.GeneralUserService;
import com.evaloper.SolidTrust.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class GeneralUserController {

    private final GeneralUserService generalUserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletRequest request;
    private final JwtAuthenticationFilter authenticationFilter;
    @PutMapping("profile-picture")
    @CachePut(value = "profilePicsCache", key = "#profilePic")
    public ResponseEntity<BankResponse<String>> profilePicsUpload(@RequestParam MultipartFile profilePic){
        if(profilePic.getSize() > AppConstants.MAX_FILE_SIZE){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BankResponse<>("FIle size exceed the normal limit"));

        }

        String token = authenticationFilter.getTokenFromRequest(request);
        String userEmail = jwtTokenProvider.getUsername(token);

        evictProfilePicCache(userEmail);

        return generalUserService.uploadProfilePics(profilePic);
    }


    @CacheEvict(value = "profilePicsCache", key = "#userEmail")
    public void evictProfilePicCache(String userEmail) {

        log.info("Evicting cache for user email {} : " + userEmail);
    }

}

