package com.tek.trp.savingsaccount.controller;

import com.tek.trp.savingsaccount.dto.PayeeRequestDTO;
import com.tek.trp.savingsaccount.entity.Payee;
import com.tek.trp.savingsaccount.service.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping(path = "/payee", produces = MediaType.APPLICATION_JSON_VALUE)
public class PayeeController {
    @Autowired
    PayeeService payeeService;

    @PostMapping("/")
    public Payee savePayee(@Valid @RequestBody PayeeRequestDTO payeeRequestDTO) {
        return payeeService.addPayee(payeeRequestDTO);
    }

    @GetMapping("/")
    public Payee getPayeeByPayeeId(@RequestParam int pid) {
        return payeeService.getPayeeByPayeeId(pid);
    }

    @GetMapping("/{cid}")
    public List<Payee> getAllPayeesByCustomerId(@PathVariable String cid) {
        return payeeService.getAllPayeesByCustomerId(cid);
    }

    @GetMapping("/{cid}/{payeeAccNum}")
    public Payee getPayeeByCustomerIdAndPAN(@PathVariable String cid, @PathVariable String payeeAccNum) {
        return payeeService.getPayeeByCustomerIdAndPAN(cid, payeeAccNum);
    }

    @GetMapping("/isActive/{id}")
    public String ifPayeeIsActive(@PathVariable int id) {
        return payeeService.isPayeeActive(id);
    }

    @PutMapping("/{id}")
    public Payee updatePayee(@PathVariable int id, @Valid @RequestBody PayeeRequestDTO payeeRequestDTO) {
        return payeeService.updatePayee(id, payeeRequestDTO);

    }

    @PutMapping("/activate/{id}")
    public Payee activatePayee(@PathVariable int id) {
        return payeeService.activatePayee(id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePayee(@PathVariable int id) {
        this.payeeService.deletePayee(id);
        return HttpStatus.OK;
    }

}
