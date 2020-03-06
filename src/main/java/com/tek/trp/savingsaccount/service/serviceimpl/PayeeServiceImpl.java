package com.tek.trp.savingsaccount.service.serviceimpl;

import com.tek.trp.savingsaccount.dto.PayeeRequestDTO;
import com.tek.trp.savingsaccount.entity.Payee;
import com.tek.trp.savingsaccount.exception.CustomerNotFoundException;
import com.tek.trp.savingsaccount.exception.PayeeNotFoundException;
import com.tek.trp.savingsaccount.repository.PayeeDao;
import com.tek.trp.savingsaccount.service.PayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.tek.trp.savingsaccount.utilities.ExceptionUtils.CUST_NOT_FOUND;
import static com.tek.trp.savingsaccount.utilities.ExceptionUtils.PAYEE_NOT_FOUND;

@Service
public class PayeeServiceImpl implements PayeeService {

    @Autowired
    PayeeDao payeeDao;

    @Autowired
    RestTemplate rs;

    private String customerServiceURL = "http://localhost:6060/customer-service/api/";

    private boolean doesCustomerExist(String cid) {
        ResponseEntity<String> custResponseEntity = rs.getForEntity(customerServiceURL + cid, String.class);
        String cust = custResponseEntity.getBody();
        return cust.toLowerCase().trim().equals("true");
    }

    @Override
    public Payee getPayeeByPayeeId(int pid) {
        Optional<Payee> payee = payeeDao.findById(pid);
        if (!payee.isPresent()) {
            throw new PayeeNotFoundException(pid, PAYEE_NOT_FOUND);
        }
        return payee.get();
    }

    @Override
    public List<Payee> getAllPayeesByCustomerId(String cid) {
        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        List<Payee> payeeList;
        List<Payee> pl = payeeDao.findByCustomerId(cid);
        if (!pl.isEmpty()) {
            payeeList = pl;
        } else {
            throw new PayeeNotFoundException(-1, "There is no payee associated with your Customer Id " + cid);
        }

        return payeeList;
    }

    @Override
    public Payee getPayeeByCustomerIdAndPAN(String cid, String payeeAccNum) {
        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        List<Payee> payeeList = payeeDao.findByCustomerIdAndPayeeAccountNumber(cid, payeeAccNum);
        if (payeeList.isEmpty())
            throw new PayeeNotFoundException(-1, "Payee Could Not be found by Payee Account Number. " + payeeAccNum);

        else
            return payeeList.get(0);

    }

    @Override
    public String isPayeeActive(int id) {
        Payee payee = getPayeeByPayeeId(id);
        if (payee.getPayeeStatus().toLowerCase().trim().equals("active"))
            return "True";
        else
            return "False";
    }

    @Override
    public Payee addPayee(PayeeRequestDTO payeeRequestDTO) {

        String cid = payeeRequestDTO.getCustomerId();
        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        Payee payee = new Payee();
        payee.setPayeeStatus("Inactive");
        payee.setCustomerId(cid);
        payee.setName(payeeRequestDTO.getName());
        payee.setNickName(payeeRequestDTO.getNickName());
        payee.setPayeeAccountNumber(payeeRequestDTO.getPayeeAccountNumber());
        payee.setPayeeBankAddress(payeeRequestDTO.getPayeeBankAddress());
        payee.setPayeeBankCity(payeeRequestDTO.getPayeeBankCity());
        payee.setPayeeBankIFSC(payeeRequestDTO.getPayeeBankIFSC());
        payee.setPayeeBankName(payeeRequestDTO.getPayeeBankName());
        payeeDao.save(payee);
        return payee;
    }

    @Override
    public Payee updatePayee(int id, PayeeRequestDTO payeeRequestDTO) {
        Payee payee = payeeDao.findById(id).orElseThrow(() -> new PayeeNotFoundException(id, PAYEE_NOT_FOUND));

        String cid = payeeRequestDTO.getCustomerId();
        if (!doesCustomerExist(cid))
            throw new CustomerNotFoundException(cid, CUST_NOT_FOUND);

        payee.setCustomerId(cid);
        payee.setName(payeeRequestDTO.getName());
        payee.setNickName(payeeRequestDTO.getNickName());
        payee.setPayeeAccountNumber(payeeRequestDTO.getPayeeAccountNumber());
        payee.setPayeeBankAddress(payeeRequestDTO.getPayeeBankAddress());
        payee.setPayeeBankCity(payeeRequestDTO.getPayeeBankCity());
        payee.setPayeeBankIFSC(payeeRequestDTO.getPayeeBankIFSC());
        payee.setPayeeBankName(payeeRequestDTO.getPayeeBankName());
        payeeDao.save(payee);
        return payee;
    }

    @Override
    public Payee activatePayee(int id) {
        Payee payee = getPayeeByPayeeId(id);
        payee.setPayeeStatus("Active");
        payeeDao.save(payee);
        return payee;
    }

    @Override
    public void deletePayee(int id) {
        Optional<Payee> payee = payeeDao.findById(id);
        if (payee.isPresent()) {
            payeeDao.delete(payee.get());
        } else {
            throw new PayeeNotFoundException(id, PAYEE_NOT_FOUND);
        }
    }
}
