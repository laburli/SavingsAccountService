package com.tek.trp.savingsaccount.service;

import com.tek.trp.savingsaccount.dto.CreditDebitRequestDTO;
import com.tek.trp.savingsaccount.dto.TransactionRequestDTO;
import com.tek.trp.savingsaccount.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction addTransaction(TransactionRequestDTO transactionRequestDTO);

    Transaction findTransactionByTransactionId(int tid);

    List<Transaction> findAllTransactionsByCustomerId(String cid);

    List<Transaction> findTransactionsByCustomerIdAndPayeeId(String cid, int pid);

    Double getBalance(String accountNum);

    String calculateSum(CreditDebitRequestDTO creditDebitRequestDTO);

    List<Transaction> getStatement(CreditDebitRequestDTO creditDebitRequestDTO);

    List<Transaction> getStatementByAccount(String accNum, CreditDebitRequestDTO creditDebitRequestDTO);
}
