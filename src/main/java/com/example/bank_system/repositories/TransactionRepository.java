package com.example.bank_system.repositories;

import com.example.bank_system.entities.Account;
import com.example.bank_system.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByOriginatingAccountId(Account originatingAccountId);
}