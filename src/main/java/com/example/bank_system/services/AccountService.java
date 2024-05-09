package com.example.bank_system.services;

import com.example.bank_system.entities.Account;
import com.example.bank_system.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (account.getUserName() == null || account.getUserName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        account.setBalance(0.0);
        return accountRepository.save(account);
    }

    public double checkAccountBalance(int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getBalance();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    public void deposit(int accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(int accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            log.error("insufficient funds");
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(currentBalance - amount);
        accountRepository.save(account);
    }
}
