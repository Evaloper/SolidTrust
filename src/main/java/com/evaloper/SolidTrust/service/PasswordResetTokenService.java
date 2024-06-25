package com.evaloper.SolidTrust.service;

import com.evaloper.SolidTrust.domain.entity.PasswordResetToken;
import com.evaloper.SolidTrust.domain.entity.UserEntity;

import java.util.Optional;

public interface PasswordResetTokenService {
    void createPasswordResetTokenForUser(UserEntity userEntity, String token);

    public String validatePasswordResetToken(String token);

    public Optional<UserEntity> findUserByPasswordToken(String token);

    public PasswordResetToken findPasswordResetToken(String token);
}
