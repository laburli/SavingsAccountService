package com.tek.trp.savingsAccount.SavingsAccountService.Controller;


import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/")
    public Transaction saveNewTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);

        return transaction;
    }

    @GetMapping("/{id}")
    public List<Transaction> getAllPayeeDetails() {
        return transactionService.findAll();
    }
}
