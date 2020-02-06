package com.tek.trp.savingsAccount.SavingsAccountService.service;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();

    Transaction addTransaction(Transaction transaction);
}
