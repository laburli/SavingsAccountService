package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Customer.Customer;
import com.tek.trp.savingsAccount.SavingsAccountService.DAO.TransactionDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
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

    public List<Transaction> findAllTransactionsByCustomerId(String cid) throws TransactionNotFoundException, CustNotFoundException {
        if (!Customer.doesCustomerExist(cid)) {
            return null;
        }

        List<Transaction> transactionList;
        try {
            List<Transaction> pl = transactionDao.findAllTransactionsByCustomerId(cid);
            if (pl != null)
                transactionList = pl;
            else
                throw new TransactionNotFoundException("There is no Transaction associated with your Customer Id : " + cid);

        } catch (Exception e) {
            throw new TransactionNotFoundException("Transaction Could not be found. Please Try again Later!");
        }
        return transactionList;

    }

    public Transaction findTransactionByTransactionId(String tid) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionDao.findById(tid);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new TransactionNotFoundException("Can't Find Any Payee with id : " + tid);
        }
    }

    public Transaction addTransaction(Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException {

        int payeeId = transaction.getPayeeId();

        //Check if Payee Exists or not
        List<Payee> payee = payeeService.getAllPayeesByCustomerId(String.valueOf(transaction.getCustomerId()));
        Payee p = payee.stream()
                .filter(payee1 -> new Integer(payeeId).equals(payee1.getID()))
                .findAny()
                .orElse(null);
        if (p == null) {
            throw new PayeeNotFoundException("Payee Not Found ! Transaction Incomplete.");
        }

        double txnAmt = transaction.getTransactionAmount();
        double avlBal = transaction.getAvailableBalance();

        //Checking Low Balance Exception Condition
        if (transaction.getTransactionType().equals("Debit")) {
            if (avlBal < txnAmt) {
                throw new IncompleteTransactionException("Low Balance! Transaction Can't Be completed. ");
            } else {
                transaction.setAvailableBalance(avlBal - txnAmt);
            }
        } else if (transaction.getTransactionType().equals("Credit")) {
            transaction.setAvailableBalance(avlBal + txnAmt);
        } else {
            throw new IncompleteTransactionException("Wrong Transaction Type! Transaction Can't Be completed.");
        }

        transactionDao.save(transaction);
        return transactionDao.findById(String.valueOf(transaction.getTransactionId())).get();
    }
}
