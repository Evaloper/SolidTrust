package com.evaloper.SolidTrust.infrastructure.controller;

import com.evaloper.SolidTrust.domain.entity.UserEntity;
import com.evaloper.SolidTrust.event.Listener.RegistrationCompleteEventListener;
import com.evaloper.SolidTrust.payload.request.ForgetPasswordRequest;
import com.evaloper.SolidTrust.payload.request.LoginRequest;
import com.evaloper.SolidTrust.payload.request.UserRequest;
import com.evaloper.SolidTrust.payload.response.APIResponse;
import com.evaloper.SolidTrust.payload.response.BankResponse;
import com.evaloper.SolidTrust.payload.response.JwtAuthResponse;
import com.evaloper.SolidTrust.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

import static com.evaloper.SolidTrust.util.AuthenticationUtils.applicationUrl;


@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Tag(name = "User Account Management APIs")
@Slf4j
public class AuthController {

    private final AuthService userService;
    private final RegistrationCompleteEventListener eventListener;


    @Operation(
            summary = "Create New User Account",
            description = "Creating a new user and assigning an account ID "
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"

    )
    @PostMapping("register-user")
    public BankResponse createAccount(@Valid @RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }

    @PostMapping("login-user")
    public ResponseEntity<APIResponse<JwtAuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping("password-reset-request")
    public String resetPasswordRequest(@RequestBody ForgetPasswordRequest passwordRequest,
                                       final HttpServletRequest request)throws MessagingException, UnsupportedEncodingException {

        Optional<UserEntity> userEntity = userService.findByEmail(passwordRequest.getEmail());

        String passwordResetUrl = "";

        if(userEntity.isPresent()){
            String passwordResetToken = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(userEntity.get(), passwordResetToken);

            passwordResetUrl = passwordResetEmailLink(userEntity.get(), applicationUrl(request), passwordResetToken);
        }
        return passwordResetUrl;

    }

    private String passwordResetEmailLink(UserEntity userEntity, String applicationUrl,
                                          String passwordResetToken) throws MessagingException, UnsupportedEncodingException {

        String url = applicationUrl+"/api/v1/auth/reset-password?token="+passwordResetToken;
        eventListener.sendPasswordResetVerificationEmail(url, userEntity);
        log.info("Click the link to reset your password :  {}", url);

        return url;
    }

    @PostMapping("password-reset")
    public String resetPassword(@RequestBody ForgetPasswordRequest passwordRequest,
                                @RequestParam("token") String token){
        String tokenVerification = userService.validatePasswordResetToken(token);

        if(!tokenVerification.equalsIgnoreCase("valid")){
            return "Invalid password reset token";
        }
        Optional<UserEntity> theUser = Optional.ofNullable(userService.findUserByPasswordToken(token));

        if(theUser.isPresent()){
            userService.changePassword(theUser.get(), passwordRequest.getNewPassword());
            return "Password has been reset successfully";
        }
        return "Invalid password reset token";

    }

    @PostMapping("change-password")
    public String changePassword(@RequestBody ForgetPasswordRequest passwordRequest){
        UserEntity userEntity = userService.findByEmail(passwordRequest.getEmail()).get();

        if(!userService.oldPasswordIsValid(userEntity, passwordRequest.getOldPassword())){
            return "Incorrect old password";
        }
        userService.changePassword(userEntity, passwordRequest.getNewPassword());

        return "Password changed successfully";

    }


}

