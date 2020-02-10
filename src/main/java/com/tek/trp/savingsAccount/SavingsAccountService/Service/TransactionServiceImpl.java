package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Customer.Customer;
import com.tek.trp.savingsAccount.SavingsAccountService.DAO.TransactionDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.ExceptionToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    PayeeService payeeService;

    public List<Transaction> findAllTransactionsByCustomerId(int cid) throws TransactionNotFoundException, CustNotFoundException {
        if (!Customer.doesCustomerExist(cid)) {
            return null;
        }

        List<Transaction> transactionList;
        List<Transaction> tl = transactionDao.findByCustomerId(cid);
        if (tl.size() != 0)
            transactionList = tl;
        else
            throw new TransactionNotFoundException(ExceptionToJson.exceptionToJsonConverter(cid, "There is no Transaction associated with your Customer Id "));

        return transactionList;

    }

    public Transaction findTransactionByTransactionId(int tid) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionDao.findById(tid);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new TransactionNotFoundException(ExceptionToJson.exceptionToJsonConverter(tid, "Can't Find Any Transaction with this ID "));
        }
    }

    public Transaction addTransaction(Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException, CustNotFoundException {

        int payeeId = transaction.getPayeeId();
        int cid = transaction.getCustomerId();
        //Check if Payee Exists or not
        List<Payee> payee = payeeService.getAllPayeesByCustomerId(transaction.getCustomerId());
        Payee p = payee.stream()
                .filter(payee1 -> payeeId == payee1.getPayeeId())
                .findAny()
                .orElse(null);

        if (p == null) {
            throw new PayeeNotFoundException(ExceptionToJson.exceptionToJsonConverter(cid, "Payee Not Found ! Transaction Incomplete."));
        }

        double txnAmt = transaction.getTransactionAmount();
        double avlBal = transaction.getAvailableBalance();

        //Checking Low Balance Exception Condition
        if (transaction.getTransactionType().equals("Debit")) {
            if (avlBal < txnAmt) {
                throw new IncompleteTransactionException(ExceptionToJson.exceptionToJsonConverter(cid, "Low Balance! Transaction Can't Be completed. "));
            } else {
                transaction.setAvailableBalance(avlBal - txnAmt);
            }
        } else if (transaction.getTransactionType().equals("Credit")) {
            transaction.setAvailableBalance(avlBal + txnAmt);
        } else {
            throw new IncompleteTransactionException(ExceptionToJson.exceptionToJsonConverter(cid, "Wrong Transaction Type! Transaction Can't Be completed."));
        }

        transactionDao.save(transaction);
        return transactionDao.findById(transaction.getTransactionId()).get();
    }
}
