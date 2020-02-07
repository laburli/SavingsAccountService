package com.tek.trp.savingsAccount.SavingsAccountService.Controller;


import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/")
    public Transaction saveNewTransaction(@RequestBody Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/{id}")
    public List<Transaction> getAllTransactionsByCustomerId(@PathVariable String id) throws CustNotFoundException, TransactionNotFoundException {
        return transactionService.findAllTransactionsByCustomerId(id);
    }

    //expects /transaction?tid = .....
    @GetMapping("/")
    public Transaction getTransactionByTransactionId(@RequestParam String tid) throws TransactionNotFoundException {
        return transactionService.findTransactionByTransactionId(tid);
    }
}
