package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {
    Transaction findTransactionByTransactionId(String tid) throws TransactionNotFoundException;

    List<Transaction> findAllTransactionsByCustomerId(String cid) throws TransactionNotFoundException, CustNotFoundException;

    Transaction addTransaction(Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException;
}
