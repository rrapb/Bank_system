package com.example.bank_system.controllers;

import com.example.bank_system.DTO.AccountDTO;
import com.example.bank_system.entities.Account;
import com.example.bank_system.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try {
            Account createdAccount = accountService.createAccount(account);
            return ResponseEntity.ok(createdAccount);
        } catch (Exception e) {
            log.error("Error creating account: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating account: " + e.getMessage());
        }
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<?> checkAccountBalance(@PathVariable int accountId) {
        try {
            double balance = accountService.checkAccountBalance(accountId);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            log.error("Error checking balance: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            List<AccountDTO> accountDTOs = new ArrayList<>();

            for (Account account : accounts) {
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setAccountId(account.getAccountId());
                accountDTO.setUserName(account.getUserName());
                accountDTO.setBalance(account.getBalance());
                accountDTO.setBankName(account.getBank().getBankName());

                accountDTOs.add(accountDTO);
            }

            return ResponseEntity.ok(accountDTOs);
        } catch (Exception e) {
            log.error("Error checking accounts: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable int accountId, @RequestParam double amount) {
        try {
            accountService.deposit(accountId, amount);
            return ResponseEntity.ok("Deposit successful");
        } catch (Exception e) {
            log.error("Error depositing amount: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error depositing amount: " + e.getMessage());
        }
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable int accountId, @RequestParam double amount) {
        try {
            accountService.withdraw(accountId, amount);
            return ResponseEntity.ok("Withdrawal successful");
        } catch (Exception e) {
            log.error("Error withdrawing amount: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error withdrawing amount: " + e.getMessage());
        }
    }
}
