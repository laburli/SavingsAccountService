package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Customer.Customer;
import com.tek.trp.savingsAccount.SavingsAccountService.DAO.PayeeDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.ExceptionToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayeeServiceImpl implements PayeeService {

    @Autowired
    PayeeDao payeeDao;

    private static String payeeNotFoundMessage = "Can't Find Any Payee with this Payee ID !";

    void initiate() {
        payeeDao.save(new Payee(1234, 5433, "Ashish Ranjan", "ashish", "SBIN0010102", "Madhapur", "State Bank Of India", "Hyderabad ", 8777777L));
        payeeDao.save(new Payee(1235, 5433, "Kashish Mehta", "kashish", "HDFC0000512", "Raj Bhavan Road", "HDFC Bank", "Hyderabad ", 8777777L));
        payeeDao.save(new Payee(1236, 5444, "Ashish Raj", "ashish", "HDFC0000512", "Raj Bhavan Road", "HDFC Bank", "Hyderabad ", 8777855L));
        payeeDao.save(new Payee(1237, 5444, "Rajvardhan Rathore", "raj", "SBIN0010102", "Madhapur", "State Bank Of India", "Hyderabad ", 8777855L));
        payeeDao.save(new Payee(1238, 5454, "Anubhav Sinha", "anubhav", "SBIN0010102", "Madhapur", "State Bank Of India", "Hyderabad ", 8777978L));
    }


    public List<Payee> getAllPayeesByCustomerId(int cid) throws PayeeNotFoundException, CustNotFoundException {
        //For the First Time only
        //initiate();

//        if (!Customer.doesCustomerExist(cid)) {
//            return null;
//        }

        List<Payee> payeeList;
        try {
            List<Payee> pl = payeeDao.findByCustomerId(cid);
            if (pl.size() != 0)
                payeeList = pl;
            else
                throw new PayeeNotFoundException(ExceptionToJson.exceptionToJsonConverter(cid, "There is no payee associated with your Customer Id  "));

        } catch (Exception e) {
            throw new PayeeNotFoundException(ExceptionToJson.exceptionToJsonConverter(cid, "Payee Could not be found. Please Try again Later!"));
        }
        return payeeList;
    }

    public Payee addPayee(Payee payee) throws CustNotFoundException {
        if (!Customer.doesCustomerExist(payee.getCustomerId()))
            return null;

        payeeDao.save(payee);
        return payeeDao.findById(payee.getPayeeId()).get();
    }

    public Payee updatePayee(int id, Payee requestPayee) throws PayeeNotFoundException {
        Payee payee = payeeDao.findById(id).orElseThrow(() -> new PayeeNotFoundException(ExceptionToJson.exceptionToJsonConverter(id, payeeNotFoundMessage)));
        if (!(requestPayee.getPayeeId() == id))
            throw new PayeeNotFoundException(ExceptionToJson.exceptionToJsonConverter(id, payeeNotFoundMessage));

        payeeDao.save(requestPayee);
        return payeeDao.findById((payee.getPayeeId())).get();
    }

    public void deletePayee(int id) throws PayeeNotFoundException {
        Optional<Payee> payee = payeeDao.findById(id);
        if (payee.isPresent()) {
            payeeDao.delete(payee.get());
        } else {
            throw new PayeeNotFoundException(ExceptionToJson.exceptionToJsonConverter(id, payeeNotFoundMessage));
        }
    }
}
