package com.tek.trp.savingsaccount.service;

import com.tek.trp.savingsaccount.dto.PayeeRequestDTO;
import com.tek.trp.savingsaccount.entity.Payee;

import java.util.List;

public interface PayeeService {

    Payee getPayeeByPayeeId(int pid);

    List<Payee> getAllPayeesByCustomerId(String cid);

    Payee getPayeeByCustomerIdAndPAN(String cid, String payeeAccNum);

    String isPayeeActive(int id);

    Payee addPayee(PayeeRequestDTO payeeRequestDTO);

    Payee updatePayee(int id, PayeeRequestDTO payeeRequestDTO);

    Payee activatePayee(int id);

    void deletePayee(int id);
}
