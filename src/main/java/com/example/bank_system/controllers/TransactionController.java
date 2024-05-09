package com.example.bank_system.controllers;


import com.example.bank_system.DTO.TransactionDTO;
import com.example.bank_system.entities.Transaction;
import com.example.bank_system.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> performTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Transaction transaction = transactionService.performTransaction(transactionDTO);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            log.error("Error performing transaction: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error performing transaction: " + e.getMessage());
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getTransactionsByAccountId(@PathVariable int accountId) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            log.error("Error checking transaction: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }
}
