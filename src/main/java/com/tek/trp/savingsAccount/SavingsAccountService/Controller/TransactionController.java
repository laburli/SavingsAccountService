package com.tek.trp.savingsAccount.SavingsAccountService.Controller;


import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.IncompleteTransactionException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.TransactionNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Service.TransactionService;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.creditDebitRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/")
    public Transaction saveNewTransaction(@RequestBody Transaction transaction) throws IncompleteTransactionException, PayeeNotFoundException, CustNotFoundException {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/{id}")
    public List<Transaction> getAllTransactionsByCustomerId(@PathVariable int id) throws CustNotFoundException, TransactionNotFoundException {
        return transactionService.findAllTransactionsByCustomerId(id);
    }

    //expects /transaction?tid = .....
    @GetMapping("/")
    public Transaction getTransactionByTransactionId(@RequestParam int tid) throws TransactionNotFoundException {
        return transactionService.findTransactionByTransactionId(tid);
    }

    //expects /transaction/{cid}?pid = .....
    @GetMapping("/{cid}")
    public List<Transaction> getTransactionsByCustomerIdAndPayeeId(@PathVariable int cid, @RequestParam int pid) throws CustNotFoundException, TransactionNotFoundException, PayeeNotFoundException {
        return transactionService.findTransactionsByCustomerIdAndPayeeId(cid, pid);
    }

    //expects /transaction/{cid}/viewCreditDebitSum/?startDate = ..... & endDate = .....
//    @GetMapping("/{cid}/viewCreditDebitSum/")
//    public String viewCreditDebitSum(@PathVariable int cid, @RequestParam String startDate, @RequestParam String endDate) throws CustNotFoundException, DateTimeParseException {
//         return transactionService.calculateCreditDebitSum(cid,startDate,endDate);
//    }


    @GetMapping("/viewCreditDebitSum")
    public String viewCreditDebitsum(@RequestBody creditDebitRequestDTO creditDebitRequestDTO) throws CustNotFoundException, DateTimeParseException {
        return transactionService.CalculateSum(creditDebitRequestDTO);

    }

    @GetMapping("/viewStatement")
    public List<Transaction> viewStatements(@RequestBody creditDebitRequestDTO creditDebitRequestDTO) throws CustNotFoundException, DateTimeParseException, TransactionNotFoundException {
        return transactionService.getStatement(creditDebitRequestDTO);
    }
}
