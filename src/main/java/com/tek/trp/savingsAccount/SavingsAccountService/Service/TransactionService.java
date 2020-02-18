package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.creditDebitRequestDTO;

import java.time.format.DateTimeParseException;
import java.util.List;

public interface TransactionService {
    Transaction findTransactionByTransactionId(int tid) throws TransactionNotFoundException;

    List<Transaction> findAllTransactionsByCustomerId(int cid) throws TransactionNotFoundException, CustNotFoundException;

    List<Transaction> findTransactionsByCustomerIdAndPayeeId(int cid, int pid) throws TransactionNotFoundException, CustNotFoundException, PayeeNotFoundException;

    Transaction addTransaction(Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException, CustNotFoundException;

    String CalculateSum(creditDebitRequestDTO creditDebitRequestDTO) throws CustNotFoundException, DateTimeParseException;

    List<Transaction> getStatement(creditDebitRequestDTO creditDebitRequestDTO) throws CustNotFoundException, TransactionNotFoundException;
}
