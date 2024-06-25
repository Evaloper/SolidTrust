package com.evaloper.SolidTrust.service.impl;

import com.evaloper.SolidTrust.domain.entity.PasswordResetToken;
import com.evaloper.SolidTrust.domain.entity.UserEntity;
import com.evaloper.SolidTrust.repository.ForgetPasswordResetRepository;
import com.evaloper.SolidTrust.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final ForgetPasswordResetRepository passwordResetRepository;

    @Override
    public void createPasswordResetTokenForUser(UserEntity userEntity, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, userEntity);
        passwordResetRepository.save(passwordResetToken);

    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordToken = passwordResetRepository.findByToken(token);

        if(passwordToken == null){
            return "Invalid verification token";
        }

        UserEntity userEntity = passwordToken.getUserEntity();
        Calendar calendar = Calendar.getInstance();

        if((passwordToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            return "Link already expired, resend link";
        }
        return "valid";
    }

    @Override
    public Optional<UserEntity> findUserByPasswordToken(String token) {
        return Optional.ofNullable(passwordResetRepository.findByToken(token).getUserEntity());
    }

    @Override
    public PasswordResetToken findPasswordResetToken(String token) {
        return passwordResetRepository.findByToken(token);
    }
}
