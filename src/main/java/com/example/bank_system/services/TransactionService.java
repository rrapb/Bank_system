package com.example.bank_system.services;

import com.example.bank_system.DTO.TransactionDTO;
import com.example.bank_system.entities.*;
import com.example.bank_system.repositories.AccountRepository;
import com.example.bank_system.repositories.BankRepository;
import com.example.bank_system.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankRepository bankRepository;

    public Transaction performTransaction(TransactionDTO transactionDTO) {
        if (transactionDTO.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Account originAccount = accountRepository.findById(transactionDTO.getOriginatingAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Originating account not found"));

        Account destinationAccount = accountRepository.findById(transactionDTO.getResultingAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        Transaction transaction;

        double amount = transactionDTO.getAmount();
        double fee;
        Bank bank = bankRepository.findById(originAccount.getBank().getBankId())
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        if (transactionDTO.getTransactionType().equals(TransactionType.FLATFEE)) {
            fee = bank.getTransactionFlatFeeAmount();

            transaction = new FlatFeeTransaction(amount, originAccount, destinationAccount, transactionDTO.getTransactionReason(), fee);

        } else if (transactionDTO.getTransactionType().equals(TransactionType.PERCENTFEE)) {
            fee = (amount * bank.getTransactionPercentFeeValue()) / 100;

            transaction = new PercentFeeTransaction(amount, originAccount, destinationAccount, transactionDTO.getTransactionReason(), fee);

        } else {
            throw new IllegalArgumentException("Unsupported transaction type");
        }

        if (originAccount.getBalance() < (amount + fee)) {
            throw new IllegalArgumentException("Insufficient funds in the origin account");
        }

        originAccount.setBalance(originAccount.getBalance() - (amount + fee));
        accountRepository.save(originAccount);

        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepository.save(destinationAccount);

        transaction = transactionRepository.save(transaction);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transaction.getAmount());
        bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + fee);
        bankRepository.save(bank);
        return transaction;
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return transactionRepository.findByOriginatingAccountId(account.orElseThrow());
    }
}
