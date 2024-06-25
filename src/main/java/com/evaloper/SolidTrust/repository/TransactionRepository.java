package com.evaloper.SolidTrust.repository;

import com.evaloper.SolidTrust.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
