package com.example.bank_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    private Account originatingAccountId;
    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    private Account resultingAccountId;
    private String transactionReason;

    public Transaction(double amount, Account originatingAccountId, Account resultingAccountId, String transactionReason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }
}
