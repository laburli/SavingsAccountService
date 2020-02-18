package com.tek.trp.savingsAccount.SavingsAccountService.Service;

import com.tek.trp.savingsAccount.SavingsAccountService.DAO.PayeeDao;
import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.CustNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Exception.PayeeNotFoundException;
import com.tek.trp.savingsAccount.SavingsAccountService.Utilities.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayeeServiceImpl implements PayeeService {

    @Autowired
    PayeeDao payeeDao;

    private static String payeeNotFoundMessage = "Can't Find Any Payee with this Payee ID !";

    public Payee getPayeeByPayeeId(int pid) throws PayeeNotFoundException {
        Optional<Payee> payee = payeeDao.findById(pid);
        if (!payee.isPresent()) {
            throw new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(pid, "There is no Payee with this Payee Id"));
        }
        return payee.get();
    }

    public List<Payee> getAllPayeesByCustomerId(int cid) throws PayeeNotFoundException, CustNotFoundException {

        List<Payee> payeeList;
        try {
            List<Payee> pl = payeeDao.findByCustomerId(cid);
            if (pl.size() != 0) {
                payeeList = pl;
            } else {
                throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "There is no payee associated with your Customer Id  "));
            }
        } catch (Exception e) {
            throw new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "Payee Could not be found. Please Try again Later!"));
        }
        return payeeList;
    }

    public Payee addPayee(Payee payee) throws CustNotFoundException {
        int cid = payee.getCustomerId();
        List<Payee> pl = payeeDao.findByCustomerId(cid);
        if (pl.size() == 0)
            throw new CustNotFoundException(ExceptionUtils.exceptionToJsonConverter(cid, "Customer Id Not found. Could Not Add Payee!"));

        payeeDao.save(payee);
        return payeeDao.findById(payee.getPayeeId()).get();
    }

    public Payee updatePayee(int id, Payee requestPayee) throws PayeeNotFoundException {
        Payee payee = payeeDao.findById(id).orElseThrow(() -> new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, payeeNotFoundMessage)));
        if (!(requestPayee.getPayeeId() == id))
            throw new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, payeeNotFoundMessage));

        payeeDao.save(requestPayee);
        return payeeDao.findById((payee.getPayeeId())).get();
    }

    public void deletePayee(int id) throws PayeeNotFoundException {
        Optional<Payee> payee = payeeDao.findById(id);
        if (payee.isPresent()) {
            payeeDao.delete(payee.get());
        } else {
            throw new PayeeNotFoundException(ExceptionUtils.exceptionToJsonConverter(id, payeeNotFoundMessage));
        }
    }
}
