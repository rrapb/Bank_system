package com.example.bank_system.services;

import com.example.bank_system.entities.Bank;
import com.example.bank_system.repositories.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void createBank(Bank bank) {
        bankRepository.save(bank);
    }

    public double getTotalTransactionFeeAmount(int bankId) {
        return bankRepository.findById(bankId).map(Bank::getTotalTransactionFeeAmount).orElseThrow();
    }

    public double getTotalTransferAmount(int bankId) {
        return bankRepository.findById(bankId).map(Bank::getTotalTransferAmount).orElseThrow();

    }
}
