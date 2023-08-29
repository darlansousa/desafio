package com.picpaysimplifiedproject.picpaysimplifiedproject.repository;

import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
