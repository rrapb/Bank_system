package com.example.bank_system.DTO;

import com.example.bank_system.entities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private double amount;
    private int originatingAccountId;
    private int resultingAccountId;
    private String transactionReason;
    private TransactionType transactionType;
}