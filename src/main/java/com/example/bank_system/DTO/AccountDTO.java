package com.example.bank_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int accountId;
    private String userName;
    private double balance;
    private String bankName;
}
