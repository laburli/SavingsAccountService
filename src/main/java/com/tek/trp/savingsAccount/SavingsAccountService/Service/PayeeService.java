package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayeeService {

    @Query()
    List<Payee> getAllPayeesByCustomerId(String id) throws PayeeNotFoundException;

    Payee addPayee(Payee payee);

    Payee updatePayee(String id, Payee payee) throws PayeeNotFoundException;

    void deletePayee(String id) throws PayeeNotFoundException;
}
