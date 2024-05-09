package com.example.bank_system.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PercentFeeTransaction extends Transaction {
    private double percentFeeValue;

    public PercentFeeTransaction(double amount, Account originatingAccountId, Account resultingAccountId, String transactionReason, double percentFeeValue) {
        super(amount, originatingAccountId, resultingAccountId, transactionReason);
        this.percentFeeValue = percentFeeValue;
    }

    public double getPercentFeeValue() {
        return percentFeeValue;
    }

    public void setPercentFeeValue(double percentFeeValue) {
        this.percentFeeValue = percentFeeValue;
    }
}

