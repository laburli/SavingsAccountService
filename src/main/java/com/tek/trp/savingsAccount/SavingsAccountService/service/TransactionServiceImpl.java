package com.tek.trp.savingsAccount.SavingsAccountService.service;

import com.tek.trp.savingsAccount.SavingsAccountService.DAO.TransactionDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
        transactionDao.save(transaction);
        return transactionDao.findById(String.valueOf(transaction.getTransactionId())).get();
    }
}
