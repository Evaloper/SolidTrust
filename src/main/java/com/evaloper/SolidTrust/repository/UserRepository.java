package com.evaloper.SolidTrust.repository;

import com.evaloper.SolidTrust.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    boolean existsByAccountNumber(String accountNumber);

    UserEntity findByAccountNumber(String accountNumber);

    Optional<UserEntity> findByEmail(String email);


}
