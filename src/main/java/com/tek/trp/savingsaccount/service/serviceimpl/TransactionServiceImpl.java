package com.tek.trp.savingsaccount.service.serviceimpl;

import com.tek.trp.savingsaccount.dto.CreditDebitRequestDTO;
import com.tek.trp.savingsaccount.dto.TransactionRequestDTO;
import com.tek.trp.savingsaccount.entity.Payee;
import com.tek.trp.savingsaccount.entity.Transaction;
import com.tek.trp.savingsaccount.exception.CustomerNotFoundException;
import com.tek.trp.savingsaccount.exception.IncompleteTransactionException;
import com.tek.trp.savingsaccount.exception.PayeeNotFoundException;
import com.tek.trp.savingsaccount.exception.TransactionNotFoundException;
import com.tek.trp.savingsaccount.repository.TransactionDao;
import com.tek.trp.savingsaccount.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.tek.trp.savingsaccount.utilities.ExceptionUtils.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    RestTemplate rs;

    private String customerServiceURL = "http://localhost:6060/customer-service/api/";


    private boolean doesCustomerExist(String cid) {
        // To Check if Customer Exists or Not
        ResponseEntity<String> custResponseEntity = rs.getForEntity(customerServiceURL + cid, String.class);
        String cust = custResponseEntity.getBody();
        return cust.toLowerCase().trim().equals("true");
    }

    @Override
    public List<Transaction> findAllTransactionsByCustomerId(String cid) throws TransactionNotFoundException, CustomerNotFoundException {
        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        List<Transaction> transactionList = transactionDao.findByCustomerId(cid);
        if (!transactionList.isEmpty())
            return transactionList;
        else
            throw new TransactionNotFoundException(-1, TRANS_NOT_FOUND_BY_CID + cid);
    }

    @Override
    public Transaction findTransactionByTransactionId(int tid) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionDao.findById(tid);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new TransactionNotFoundException(tid, TRANS_NOT_FOUND);
        }
    }

    @Override
    public List<Transaction> findTransactionsByCustomerIdAndPayeeId(String cid, int pid) throws TransactionNotFoundException, CustomerNotFoundException, PayeeNotFoundException {
        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        // To Check if Payee Exists or Not
        ResponseEntity<Payee> payeeResponseEntity = rs.getForEntity("http://localhost:8080/payee/?pid=" + pid, Payee.class);
        Payee payee = payeeResponseEntity.getBody();
        if (payee == null)
            throw new PayeeNotFoundException(pid, PAYEE_NOT_FOUND);

        List<Transaction> transactionList = transactionDao.findByCustomerIdAndPayeeId(cid, pid);
        if (transactionList.isEmpty()) {
            throw new TransactionNotFoundException(-1, "There is no Transaction associated with your Customer Id " + cid + " & Payee Id " + pid);
        }
        return transactionList;
    }

    @Override
    public Double getBalance(String accountNum) {

        Double balance = transactionDao.getBalance(accountNum);
        if (balance == null)
            return 0.0;
        else
            return balance;
    }

    @Override
    public Transaction addTransaction(TransactionRequestDTO transactionRequestDTO) throws IncompleteTransactionException, PayeeNotFoundException, CustomerNotFoundException {
        double minBal = 1000.0;

        Transaction transaction = new Transaction();
        transaction.setTransactionId(0);

        String cid = transactionRequestDTO.getCustomerId();
        int payeeId = transactionRequestDTO.getPayeeId();
        if (!doesCustomerExist(cid)) {
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);
        } else {
            transaction.setCustomerId(cid);
            transaction.setCustomerAccountNumber(transactionRequestDTO.getCustomerAccountNumber());
        }

        // To verify if payee is present & active or not for that customerId
        ResponseEntity<Payee[]> payeeListResponseEntity = rs.getForEntity("http://localhost:8080/payee/" + cid, Payee[].class);
        List<Payee> payeeList = Arrays.asList(payeeListResponseEntity.getBody());
        Payee payee = payeeList.stream()
                .filter(payee1 -> payeeId == payee1.getPayeeId())
                .findAny()
                .orElse(null);

        if (payee == null) {
            throw new PayeeNotFoundException(-1, "Payee Not Found for Customer ID " + cid + "! Transaction Incomplete.");
        } else {
            if (payee.getPayeeStatus().trim().equalsIgnoreCase("active")) {
                transaction.setPayeeId(payee.getPayeeId());
                transaction.setPayeeName(payee.getName());
            } else
                throw new PayeeNotFoundException(payeeId, PAYEE_NOT_ACTIVE);
        }

        String transactionType = transactionRequestDTO.getTransactionType();
        transaction.setTransactionType(transactionType);

        double txnAmt = transactionRequestDTO.getTransactionAmount();
        transaction.setTransactionAmount(txnAmt);

        double avlBal = getBalance(transactionRequestDTO.getCustomerAccountNumber());

        if (transactionType.equalsIgnoreCase("credit")) {
            transaction.setAvailableBalance(avlBal + txnAmt);
        } else if (transactionType.equalsIgnoreCase("debit")) {
            if (avlBal < minBal) {
                throw new IncompleteTransactionException(cid, LOW_BAL + minBal);
            } else if ((avlBal + minBal) <= txnAmt) {
                throw new IncompleteTransactionException(cid, LOW_BAL + minBal);
            } else {
                transaction.setAvailableBalance(avlBal - txnAmt);
            }
        } else {
            throw new IncompleteTransactionException(cid, INVALID_TRANS_TYPE);
        }

        LocalDateTime currTime = LocalDateTime.now();
        transaction.setTransactionTime(currTime);

        transactionDao.save(transaction);
        return transaction;
    }

    @Override
    public String calculateSum(CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException {
        String cid = creditDebitRequestDTO.getCustomerId();
        LocalDate startDate = creditDebitRequestDTO.getStartDate();
        LocalDate endDate = creditDebitRequestDTO.getEndDate();

        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        int debitSum = debitSum(cid, startDate, endDate, "Debit");
        int creditSum = creditSum(cid, startDate, endDate, "Credit");
        return "{\"customerId\" : " + cid + ",\"debitSum\" : " + debitSum + ",\"creditSum\" : " + creditSum + "}";

    }

    private int creditSum(String id, LocalDate start, LocalDate end, String transactiontype) {
        try {
            return transactionDao.sumByDatesBetween(id, transactiontype, start, end);
        } catch (Exception e) {
            return 0;
        }


    }

    private int debitSum(String id, LocalDate start, LocalDate end, String transactiontype) {
        try {
            return transactionDao.sumByDatesBetween(id, transactiontype, start, end);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<Transaction> getStatement(CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException, TransactionNotFoundException {

        String cid = creditDebitRequestDTO.getCustomerId();
        LocalDate startDate = creditDebitRequestDTO.getStartDate();
        LocalDate endDate = creditDebitRequestDTO.getEndDate().plusDays(1);

        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        List<Transaction> transactionList = transactionDao.viewStatement(cid, startDate, endDate);
        if (transactionList.isEmpty()) {
            throw new TransactionNotFoundException(-1, TRANS_NOT_FOUND_BY_CID + cid);
        }

        return transactionList;
    }

    @Override
    public List<Transaction> getStatementByAccount(String accNum, CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException, TransactionNotFoundException {
        String cid = creditDebitRequestDTO.getCustomerId();
        LocalDate startDate = creditDebitRequestDTO.getStartDate();
        LocalDate endDate = creditDebitRequestDTO.getEndDate().plusDays(1);

        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        List<Transaction> transactionList = transactionDao.viewStatementByAccount(cid, accNum, startDate, endDate);
        if (transactionList.isEmpty()) {
            throw new TransactionNotFoundException(-1, TRANS_NOT_FOUND_BY_CID + cid);
        }

        return transactionList;
    }
}
