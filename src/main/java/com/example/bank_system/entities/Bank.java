package com.example.bank_system.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;
    private String bankName;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;
}