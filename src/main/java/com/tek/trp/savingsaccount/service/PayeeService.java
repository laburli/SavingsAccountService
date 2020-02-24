package com.tek.trp.savingsaccount.service;

import com.tek.trp.savingsaccount.dto.PayeeRequestDTO;
import com.tek.trp.savingsaccount.entity.Payee;
import com.tek.trp.savingsaccount.exception.CustomerNotFoundException;
import com.tek.trp.savingsaccount.exception.PayeeNotFoundException;

import java.util.List;

public interface PayeeService {

    Payee getPayeeByPayeeId(int pid) throws PayeeNotFoundException;

    List<Payee> getAllPayeesByCustomerId(String cid) throws PayeeNotFoundException, CustomerNotFoundException;

    Payee getPayeeByCustomerIdAndPAN(String cid, String payeeAccNum) throws PayeeNotFoundException, CustomerNotFoundException;

    String isPayeeActive(int id) throws PayeeNotFoundException;

    Payee addPayee(PayeeRequestDTO payeeRequestDTO) throws CustomerNotFoundException;

    Payee updatePayee(int id, PayeeRequestDTO payeeRequestDTO) throws PayeeNotFoundException, CustomerNotFoundException;

    Payee activatePayee(int id) throws PayeeNotFoundException;

    void deletePayee(int id) throws PayeeNotFoundException;
}
