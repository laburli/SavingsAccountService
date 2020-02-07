package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Customer.Customer;
import com.tek.trp.savingsAccount.SavingsAccountService.DAO.PayeeDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayeeServiceImpl {
    @Autowired
    PayeeDao payeeDao;

    private static String payeeNotFoundMessage = "Can't Find Any Payee with id : ";

    void initiate() {
        payeeDao.save(new Payee(1234, 5433, "Ashish Ranjan", "ashish", "", "", "", "", 8777777L));
        payeeDao.save(new Payee(1235, 5433, "Kashish Mehta", "kashish", "", "", "", "", 8777777L));
        payeeDao.save(new Payee(1236, 5444, "Ashish Raj", "ashish", "", "", "", "", 8777855L));
        payeeDao.save(new Payee(1237, 5444, "Rajvardhan Rathore", "raj", "", "", "", "", 8777855L));
        payeeDao.save(new Payee(1238, 5454, "Anubhav Sinha", "anubhav", "", "", "", "", 8777978L));
    }


    public List<Payee> getAllPayeesByCustomerId(String cid) throws PayeeNotFoundException, CustNotFoundException {
        //For the First Time only
        //initiate();

        if (!Customer.doesCustomerExist(cid)) {
            return null;
        }

        List<Payee> payeeList;
        try {
            List<Payee> pl = payeeDao.findAllPayeesByCustomerId(cid);
            if (pl != null)
                payeeList = pl;
            else
                throw new PayeeNotFoundException("There is no payee associated with your Customer Id : " + cid);

        } catch (Exception e) {
            throw new PayeeNotFoundException("Payee Could not be found. Please Try again Later!");
        }
        return payeeList;
    }

    public Payee addPayee(Payee payee) throws CustNotFoundException {
        if (!Customer.doesCustomerExist(String.valueOf(payee.getCustomerId())))
            return null;

        payeeDao.save(payee);
        return payeeDao.findById(String.valueOf(payee.getID())).get();
    }

    Payee updatePayee(String id, Payee requestPayee) throws PayeeNotFoundException {
        Payee payee = payeeDao.findById(id).orElseThrow(() -> new PayeeNotFoundException(payeeNotFoundMessage + id));
        if (!(requestPayee.getID() == Integer.parseInt(id)))
            throw new PayeeNotFoundException(payeeNotFoundMessage + id);

        payeeDao.save(payee);
        return payeeDao.findById(String.valueOf(payee.getID())).get();
    }

    public void deletePayee(String id) throws PayeeNotFoundException {
        Optional<Payee> payee = payeeDao.findById(id);
        if (payee.isPresent()) {
            payeeDao.delete(payee.get());
        } else {
            throw new PayeeNotFoundException(payeeNotFoundMessage + id);
        }
    }
}
