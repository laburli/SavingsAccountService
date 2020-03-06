package com.tek.trp.savingsaccount.controller;

import com.tek.trp.savingsaccount.dto.CreditDebitRequestDTO;
import com.tek.trp.savingsaccount.dto.TransactionRequestDTO;
import com.tek.trp.savingsaccount.entity.Transaction;
import com.tek.trp.savingsaccount.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping(path = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/")
    public Transaction saveNewTransaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        return transactionService.addTransaction(transactionRequestDTO);
    }

    @GetMapping("/{cid}")
    public List<Transaction> getAllTransactionsByCustomerId(@PathVariable String cid) {
        return transactionService.findAllTransactionsByCustomerId(cid);
    }

    @GetMapping("/getBalance/{accNum}")
    public Double getCurrentBalance(@PathVariable String accNum) {
        return transactionService.getBalance(accNum);
    }

    //expects /transaction?tid = .....
    @GetMapping("/")
    public Transaction getTransactionByTransactionId(@RequestParam int tid) {
        return transactionService.findTransactionByTransactionId(tid);
    }

    //expects /transaction/{cid}?pid = .....
    @GetMapping("/{cid}/{pid}")
    public List<Transaction> getTransactionsByCustomerIdAndPayeeId(@PathVariable String cid, @PathVariable int pid) {
        return transactionService.findTransactionsByCustomerIdAndPayeeId(cid, pid);
    }


    @PostMapping("/viewCreditDebitSum")
    public String viewCreditDebitsum(@Valid @RequestBody CreditDebitRequestDTO creditDebitRequestDTO) {
        return transactionService.calculateSum(creditDebitRequestDTO);

    }

    @PostMapping("/viewStatement")
    public List<Transaction> viewStatements(@Valid @RequestBody CreditDebitRequestDTO creditDebitRequestDTO) {
        return transactionService.getStatement(creditDebitRequestDTO);
    }

    @PostMapping("/viewStatement/{accNum}")
    public List<Transaction> viewStatementsByAccount(@PathVariable String accNum, @Valid @RequestBody CreditDebitRequestDTO creditDebitRequestDTO) {
        return transactionService.getStatementByAccount(accNum, creditDebitRequestDTO);
    }
}
