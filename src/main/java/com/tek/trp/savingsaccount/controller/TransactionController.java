package com.tek.trp.savingsaccount.controller;


import com.tek.trp.savingsaccount.dto.CreditDebitRequestDTO;
import com.tek.trp.savingsaccount.dto.TransactionRequestDTO;
import com.tek.trp.savingsaccount.entity.Transaction;
import com.tek.trp.savingsaccount.exception.CustomerNotFoundException;
import com.tek.trp.savingsaccount.exception.IncompleteTransactionException;
import com.tek.trp.savingsaccount.exception.PayeeNotFoundException;
import com.tek.trp.savingsaccount.exception.TransactionNotFoundException;
import com.tek.trp.savingsaccount.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/")
    public Transaction saveNewTransaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) throws IncompleteTransactionException, PayeeNotFoundException, CustomerNotFoundException {
        return transactionService.addTransaction(transactionRequestDTO);
    }

    @GetMapping("/{cid}")
    public List<Transaction> getAllTransactionsByCustomerId(@PathVariable String cid) throws CustomerNotFoundException, TransactionNotFoundException {
        return transactionService.findAllTransactionsByCustomerId(cid);
    }

    @GetMapping("/getBalance/{accNum}")
    public Double getCurrentBalance(@PathVariable String accNum) {
        return transactionService.getBalance(accNum);
    }

    //expects /transaction?tid = .....
    @GetMapping("/")
    public Transaction getTransactionByTransactionId(@RequestParam int tid) throws TransactionNotFoundException {
        return transactionService.findTransactionByTransactionId(tid);
    }

    //expects /transaction/{cid}?pid = .....
    @GetMapping("/{cid}/{pid}")
    public List<Transaction> getTransactionsByCustomerIdAndPayeeId(@PathVariable String cid, @PathVariable int pid) throws CustomerNotFoundException, TransactionNotFoundException, PayeeNotFoundException {
        return transactionService.findTransactionsByCustomerIdAndPayeeId(cid, pid);
    }


    @PostMapping("/viewCreditDebitSum")
    public String viewCreditDebitsum(@Valid @RequestBody CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException {
        return transactionService.calculateSum(creditDebitRequestDTO);

    }

    @PostMapping("/viewStatement")
    public List<Transaction> viewStatements(@Valid @RequestBody CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException, TransactionNotFoundException {
        return transactionService.getStatement(creditDebitRequestDTO);
    }

    @PostMapping("/viewStatement/{accNum}")
    public List<Transaction> viewStatementsByAccount(@PathVariable String accNum, @Valid @RequestBody CreditDebitRequestDTO creditDebitRequestDTO) throws CustomerNotFoundException, TransactionNotFoundException {
        return transactionService.getStatementByAccount(accNum, creditDebitRequestDTO);
    }
}
