package com.tek.trp.savingsAccount.SavingsAccountService.Controller;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Service.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/payee", produces = MediaType.APPLICATION_JSON_VALUE)
public class PayeeController {
    @Autowired
    PayeeService payeeService;

    @PostMapping("/")
    public ResponseEntity<Payee> savePayee(@RequestBody Payee payee) {
        return ResponseEntity.ok(payeeService.addPayee(payee));
    }

    @GetMapping("/{cid}")
    public List<Payee> getAllPayeesByCustomerId(@PathVariable String cid) throws PayeeNotFoundException {
        return payeeService.getAllPayeesByCustomerId(cid);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable String id) throws PayeeNotFoundException {
        this.payeeService.deletePayee(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payee> updatePayee(@PathVariable("id") String id, @RequestBody Payee payee) throws PayeeNotFoundException {
        return ResponseEntity.ok(payeeService.updatePayee(id, payee));

    }

}
