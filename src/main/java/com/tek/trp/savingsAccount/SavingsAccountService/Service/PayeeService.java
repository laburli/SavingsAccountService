package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;

import java.util.List;

public interface PayeeService {

    List<Payee> getAllPayeesByCustomerId(int cid) throws PayeeNotFoundException, CustNotFoundException;

    Payee addPayee(Payee payee) throws CustNotFoundException;

    Payee updatePayee(int id, Payee payee) throws PayeeNotFoundException;

    void deletePayee(int id) throws PayeeNotFoundException;
}
