package com.example.bank_system.controllers;

import com.example.bank_system.entities.Bank;
import com.example.bank_system.services.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/{bankId}/total-transaction-fee")
    public ResponseEntity<?> getTotalTransactionFeeAmount(@PathVariable int bankId) {
        try {
            double totalTransactionFeeAmount = bankService.getTotalTransactionFeeAmount(bankId);
            return ResponseEntity.ok(totalTransactionFeeAmount);
        } catch (IllegalArgumentException e) {
            log.error("Error checking transaction fee amount for bank with ID {}: {}", bankId, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{bankId}/total-transfer-amount")
    public ResponseEntity<?> getTotalTransferAmount(@PathVariable int bankId) {
        try {
            double totalTransferAmount = bankService.getTotalTransferAmount(bankId);
            return ResponseEntity.ok(totalTransferAmount);
        } catch (IllegalArgumentException e) {
            log.error("Error checking total transfer amount for bank with ID {}: {}", bankId, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBank(@RequestBody Bank bank) {
        try {
            bankService.createBank(bank);
            return ResponseEntity.ok("Bank created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating a bank: " + e.getMessage());
        }
    }
}

