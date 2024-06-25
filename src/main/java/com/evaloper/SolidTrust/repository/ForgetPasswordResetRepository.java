package com.evaloper.SolidTrust.repository;
import com.evaloper.SolidTrust.domain.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetPasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}

