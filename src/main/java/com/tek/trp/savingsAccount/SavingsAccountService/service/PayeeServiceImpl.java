package com.tek.trp.savingsAccount.SavingsAccountService.service;

import com.tek.trp.savingsAccount.SavingsAccountService.DAO.PayeeDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayeeServiceImpl {
    @Autowired
    PayeeDao payeeDao;

    void initiate() {
        payeeDao.save(new Payee(1234, 5433, "Ashish Ranjan", 8777777L));
        payeeDao.save(new Payee(1235, 5433, "Kashish Mehta", 8777777L));
        payeeDao.save(new Payee(1236, 5444, "Aditya Raj", 8777855L));
        payeeDao.save(new Payee(1237, 5444, "Rajvardhan Rathore", 8777855L));
        payeeDao.save(new Payee(1238, 5454, "Anubhav Sinha ", 8777978L));
    }

    private boolean doesCustomerExist(String Id) {
        // add throws CustomerNotFoundException in the method
        //connect with CustomerServiceImpl to find if customer exists  or not
        return true;
    }

    List<Payee> getAllPayeesByCustomerId(String id) throws PayeeNotFoundException {
        //For the First Time only
        //initiate();

        if (!doesCustomerExist(id)) {
            return null;
        }

        List<Payee> payeeList;
        try {
            List<Payee> pl = payeeDao.findAllPayeesByCustomerId(id);
            if (pl != null)
                payeeList = pl;
            else
                throw new PayeeNotFoundException("There is no payee associated with your Customer Id : " + id);

        } catch (Exception e) {
            throw new PayeeNotFoundException("Payee Could not be found. Please Try again Later!");
        }
        return payeeList;
    }

    Payee addPayee(Payee payee) {
        if (!doesCustomerExist(String.valueOf(payee.getCustomerId()))) {
            return null;
        }
        payeeDao.save(payee);
        return payeeDao.findById(String.valueOf(payee.getID())).get();
    }

}
