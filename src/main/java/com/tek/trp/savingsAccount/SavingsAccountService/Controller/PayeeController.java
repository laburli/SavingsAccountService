package com.tek.trp.savingsAccount.SavingsAccountService.Controller;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Service.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/payee", produces = MediaType.APPLICATION_JSON_VALUE)
public class PayeeController {
    @Autowired
    PayeeService payeeService;

    @PostMapping("/")
    public Payee savePayee(@RequestBody Payee payee) throws CustNotFoundException {
        return payeeService.addPayee(payee);
    }

    @GetMapping("/{cid}")
    public List<Payee> getAllPayeesByCustomerId(@PathVariable int cid) throws PayeeNotFoundException, CustNotFoundException {
        return payeeService.getAllPayeesByCustomerId(cid);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable int id) throws PayeeNotFoundException {
        this.payeeService.deletePayee(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public Payee updatePayee(@PathVariable("id") int id, @RequestBody Payee payee) throws PayeeNotFoundException {
        return payeeService.updatePayee(id, payee);

    }

}
