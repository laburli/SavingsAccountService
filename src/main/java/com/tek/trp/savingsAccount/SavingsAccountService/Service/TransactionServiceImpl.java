package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.DAO.TransactionDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.ExceptionUtils;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.creditDebitRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    PayeeService payeeService;

    @Autowired
    RestTemplate rs;

    public List<Transaction> findAllTransactionsByCustomerId(int cid) throws TransactionNotFoundException, CustNotFoundException {
        List<Transaction> Customer = transactionDao.findByCustomerId(cid);
        if (Customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "There is no Customer with this id."));
        }
        List<Transaction> transactionList;
        List<Transaction> tl = transactionDao.findByCustomerId(cid);
        if (tl.size() != 0)
            transactionList = tl;
        else
            throw new TransactionNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "There is no Transaction associated with your Customer Id "));

        return transactionList;

    }

    public Transaction findTransactionByTransactionId(int tid) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionDao.findById(tid);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new TransactionNotFoundException(ExceptionUtils.exceptionToJsonConverter(tid, "Can't Find Any Transaction with this ID "));
        }
    }

    public List<Transaction> findTransactionsByCustomerIdAndPayeeId(int cid, int pid) throws TransactionNotFoundException, CustNotFoundException, PayeeNotFoundException {
        List<Transaction> customer = transactionDao.findByCustomerId(cid);
        if (customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "There is no Customer with this id."));
        }
        List<Transaction> payee = transactionDao.findByPayeeId(pid);
        if (payee.isEmpty()) {
            throw new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(pid, "There is no Payee with this Payee Id"));
        }

        List<Transaction> transactionList = transactionDao.findByCustomerIdAndPayeeId(cid, pid);
        if (transactionList.size() == 0) {
            throw new TransactionNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "There is no Transaction associated with your Customer Id & Payee Id"));
        }
        return transactionList;
    }

    public Transaction addTransaction(Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException, CustNotFoundException {

        int payeeId = transaction.getPayeeId();
        int cid = transaction.getCustomerId();

        ResponseEntity<Payee> pay = rs.getForEntity("http://localhost:8080/payee/?pid=" + payeeId, Payee.class);

        List<Transaction> Customer = transactionDao.findByCustomerId(cid);
        if (Customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "There is no Customer with this id."));
        }

        List<Payee> payee = payeeService.getAllPayeesByCustomerId(transaction.getCustomerId());
        Payee p = payee.stream()
                .filter(payee1 -> payeeId == payee1.getPayeeId())
                .findAny()
                .orElse(null);

        if (p == null) {
            throw new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "Payee Not Found ! Transaction Incomplete."));
        }

        double txnAmt = transaction.getTransactionAmount();
        double avlBal = transaction.getAvailableBalance();

        if (avlBal < 1000) {
            throw new IncompleteTransactionException(ExceptionUtils.exceptionToJsonConverter(cid, "Avaliable Balance is less than Rs 1000"));
        }

        //Checking Low Balance Exception Condition
        if (transaction.getTransactionType().toLowerCase().trim().equals("debit")) {
            if (avlBal < txnAmt) {
                throw new IncompleteTransactionException(ExceptionUtils.exceptionToJsonConverter(cid, "Low Balance! Transaction Can't Be completed. "));
            } else {
                transaction.setAvailableBalance(avlBal - txnAmt);
            }
        } else if (transaction.getTransactionType().toLowerCase().trim().equals("credit")) {
            transaction.setAvailableBalance(avlBal + txnAmt);
        } else {
            throw new IncompleteTransactionException(ExceptionUtils.exceptionToJsonConverter(cid, "Wrong Transaction Type! Transaction Can't Be completed."));
        }

        transactionDao.save(transaction);
        return transactionDao.findById(transaction.getTransactionId()).get();
    }

    public String CalculateSum(creditDebitRequestDTO creditDebitRequestDTO) throws CustNotFoundException {
        int id = creditDebitRequestDTO.getCustomerId();
        LocalDateTime startDate = creditDebitRequestDTO.getStartDate();
        LocalDateTime endDate = creditDebitRequestDTO.getEndDate();

        List<Transaction> Customer = transactionDao.findByCustomerId(id);
        if (Customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, "There is no customer with this id"));
        } else {

            int debitsum = DebitSum(id, startDate, endDate, "Debit");
            int creditsum = CreditSum(id, startDate, endDate, "Credit");
            String response = "{\"customerId\" : " + id + ",\"debitSum\" : " + debitsum + ",\"creditSum\" : " + creditsum + "}";
            return response;
        }
    }

    private int CreditSum(int id, LocalDateTime start, LocalDateTime end, String transactiontype) {
        try {
            return transactionDao.sumByDatesBetween(id, transactiontype, start, end);
        } catch (Exception e) {
            return 0;
        }


    }

    private int DebitSum(int id, LocalDateTime start, LocalDateTime end, String transactiontype) {
        try {
            return transactionDao.sumByDatesBetween(id, transactiontype, start, end);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<Transaction> getStatement(creditDebitRequestDTO creditDebitRequestDTO) throws CustNotFoundException, TransactionNotFoundException {

        int id = creditDebitRequestDTO.getCustomerId();
        LocalDateTime startDate = creditDebitRequestDTO.getStartDate();
        LocalDateTime endDate = creditDebitRequestDTO.getEndDate().plusDays(1);

        List<Transaction> customer = transactionDao.findByCustomerId(id);

        if (customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, "There is no customer with this id"));
        } else {

            List<Transaction> transactionList = transactionDao.getViewStatement(id, startDate, endDate);
            if (transactionList.isEmpty()) {
                throw new TransactionNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, "There is no Transaction associated with your Customer Id "));
            }

            long no = Duration.between(startDate, endDate).toDays();
            if (no >= 180) {
                throw new TransactionNotFoundException("You can see ViewStatement of last 6 months only");
            }
            return transactionList;
        }
    }
}
