package com.evaloper.SolidTrust.service;
import com.evaloper.SolidTrust.domain.entity.UserEntity;
import com.evaloper.SolidTrust.payload.request.LoginRequest;
import com.evaloper.SolidTrust.payload.request.UserRequest;
import com.evaloper.SolidTrust.payload.response.APIResponse;
import com.evaloper.SolidTrust.payload.response.BankResponse;
import com.evaloper.SolidTrust.payload.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AuthService {
    BankResponse registerUser(UserRequest userRequest);

    ResponseEntity<APIResponse<JwtAuthResponse>> login(LoginRequest loginRequest);

    void changePassword(UserEntity theUser, String newPassword);

    String validatePasswordResetToken(String token);

    UserEntity findUserByPasswordToken(String token);

    void createPasswordResetTokenForUser(UserEntity userEntity, String token);

    boolean oldPasswordIsValid(UserEntity userEntity, String oldPassword);

    Optional<UserEntity> findByEmail(String email);


}

