package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Customer.Customer;
import com.tek.trp.savingsAccount.SavingsAccountService.DAO.TransactionDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.ExceptionUtils;
import com.tek.trp.savingsAccount.SavingsAccountService.request.ViewCreditDebitRequest;
import com.tek.trp.savingsAccount.SavingsAccountService.request.ViewCreditDebitSumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Transaction addTransaction(Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException, CustNotFoundException {

        int payeeId = transaction.getPayeeId();
        int cid = transaction.getCustomerId();

//        if (!Customer.doesCustomerExist(cid)) {
//            return null;
//        }

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

    public ViewCreditDebitSumResponse CalculateSum(ViewCreditDebitRequest viewCreditDebitRequest) throws CustNotFoundException {
        int id = viewCreditDebitRequest.getCustomerId();
        String startDate = String.valueOf(viewCreditDebitRequest.getStartDate());
        String endDate = String.valueOf(viewCreditDebitRequest.getEndDate());
        List<Transaction> Customer = transactionDao.findByCustomerId(id);
        if (Customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, "There is no customer with this id"));
        } else {
            ViewCreditDebitSumResponse viewCreditDebitSumResponse = new ViewCreditDebitSumResponse();

            int debitsum = DebitSum(id, startDate, endDate, "debit");
            int creditsum = CreditSum(id, startDate, endDate, "credit");
            viewCreditDebitSumResponse.setDebitsum(debitsum);
            viewCreditDebitSumResponse.setCreditsum(creditsum);
            return viewCreditDebitSumResponse;
        }
    }

    private int CreditSum(int id, String startdate, String enddate, String transactiontype) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime start = LocalDate.parse(startdate, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(enddate, formatter).atStartOfDay();
        try {
            return transactionDao.sumByDatesBetween(id, start, end, transactiontype);
        } catch (Exception e) {
            return 0;
        }


    }

    private int DebitSum(int id, String startdate, String enddate, String transactiontype) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime start = LocalDate.parse(startdate, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(enddate, formatter).atStartOfDay();
        try {
            return transactionDao.sumByDatesBetween(id, start, end, transactiontype);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<Transaction> getStatement(ViewCreditDebitRequest viewCreditDebitRequest) throws CustNotFoundException, TransactionNotFoundException {

        long no = Duration.between(viewCreditDebitRequest.getStartDate(), viewCreditDebitRequest.getEndDate()).toDays();
        if (no >= 180) {
            throw new TransactionNotFoundException("you can see ViewStatement of last 6 months only");
        }

        int id = viewCreditDebitRequest.getCustomerId();
        LocalDateTime startDate = viewCreditDebitRequest.getStartDate();
        LocalDateTime endDate = viewCreditDebitRequest.getEndDate().plusDays(1);

        Transaction entity = new Transaction();

        List<Transaction> customer = transactionDao.findByCustomerId(id);

        if (customer.isEmpty()) {
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, "There is no customer with this id"));
        } else {
            List<Transaction> transactionList = transactionDao.getViewStatement(id, startDate, endDate);
            if (transactionList.isEmpty()) {
                throw new TransactionNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, "There is no Transaction associated with your Customer Id "));
            }
            return transactionList;
        }
    }
}
