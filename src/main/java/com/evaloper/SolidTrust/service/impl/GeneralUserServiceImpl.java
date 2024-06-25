package com.evaloper.SolidTrust.service.impl;

import com.evaloper.SolidTrust.domain.entity.UserEntity;
import com.evaloper.SolidTrust.infrastructure.security.JwtAuthenticationFilter;
import com.evaloper.SolidTrust.infrastructure.security.JwtTokenProvider;
import com.evaloper.SolidTrust.payload.response.BankResponse;
import com.evaloper.SolidTrust.repository.UserRepository;
import com.evaloper.SolidTrust.service.GeneralUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GeneralUserServiceImpl implements GeneralUserService {
    private final FileUploadImpl fileUpload;
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter authenticationFilter;
    private final HttpServletRequest request;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public ResponseEntity<BankResponse<String>> uploadProfilePics(MultipartFile profilePics) {

        String token = authenticationFilter.getTokenFromRequest(request);
        String email = jwtTokenProvider.getUsername(token);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        String fileUrl = null;

        try{
            if(userEntityOptional.isPresent()){
                fileUrl = fileUpload.uploadFile(profilePics);

                UserEntity userEntity = userEntityOptional.get();
                userEntity.setProfilePicture(fileUrl);

                userRepository.save(userEntity);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(
                new BankResponse<>(
                        "Uploaded Successfully",
                        fileUrl
                )
        );
    }
}

