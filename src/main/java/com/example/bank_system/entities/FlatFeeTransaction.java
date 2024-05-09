package com.example.bank_system.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FlatFeeTransaction extends Transaction {
    private double flatFeeAmount;


    public FlatFeeTransaction(double amount, Account originatingAccountId, Account resultingAccountId, String transactionReason, double flatFeeAmount) {
        super(amount, originatingAccountId, resultingAccountId, transactionReason);
        this.flatFeeAmount = flatFeeAmount;
    }

    public double getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public void setFlatFeeAmount(double percentFeeValue) {
        this.flatFeeAmount = flatFeeAmount;
    }
}