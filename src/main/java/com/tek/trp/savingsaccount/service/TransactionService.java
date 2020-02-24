package com.tek.trp.savingsaccount.service;

import com.tek.trp.savingsaccount.dto.CreditDebitRequestDTO;
import com.tek.trp.savingsaccount.dto.TransactionRequestDTO;
import com.tek.trp.savingsaccount.entity.Transaction;
import com.tek.trp.savingsaccount.exception.CustomerNotFoundException;
import com.tek.trp.savingsaccount.exception.IncompleteTransactionException;
import com.tek.trp.savingsaccount.exception.PayeeNotFoundException;
import com.tek.trp.savingsaccount.exception.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {

    Transaction addTransaction(TransactionRequestDTO transactionRequestDTO) throws IncompleteTransactionException, PayeeNotFoundException, CustomerNotFoundException;

    Transaction findTransactionByTransactionId(int tid) throws TransactionNotFoundException;

    List<Transaction> findAllTransactionsByCustomerId(String cid) throws TransactionNotFoundException, CustomerNotFoundException;

    List<Transaction> findTransactionsByCustomerIdAndPayeeId(String cid, int pid) throws TransactionNotFoundException, CustomerNotFoundException, PayeeNotFoundException;

    Double getBalance(String accountNum);

    String calculateSum(CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException;

    List<Transaction> getStatement(CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException, TransactionNotFoundException;

    List<Transaction> getStatementByAccount(String accNum, CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException, TransactionNotFoundException;
}
