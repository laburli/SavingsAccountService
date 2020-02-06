package com.tek.trp.savingsAccount.SavingsAccountService.Controller;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.service.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payee")
public class PayeeController {
    @Autowired
    PayeeService payeeService;

    @PostMapping("/")
    public Payee savePayee(@RequestBody Payee payee) {
        return payeeService.addPayee(payee);

    }

    @GetMapping("/{id}")
    public List<Payee> getAllPayeesByCustomerId(@PathVariable String id) throws PayeeNotFoundException {
        return payeeService.getAllPayeesByCustomerId(id);
    }

}
