package com.tek.trp.savingsaccount.repository;

import com.tek.trp.savingsaccount.entity.Payee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PayeeDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PayeeDao payeeDao;

    public void testSavePayee() {
//        Payee payee = getMockPayee();
//        Payee savedInDb = testEntityManager.persist(payee);
//        Optional<Payee> getFromDb = payeeDao.findById(savedInDb.getPayeeId());
//
//        if (getFromDb.isPresent())
//            assertEquals(getFromDb.get(), payee);

    }


    public void testFindByCustomerId() {
//        Payee payee = getMockPayee();
//        Payee savedInDb = testEntityManager.persist(payee);
//        List<Payee> getFromDb = payeeDao.findByCustomerId(savedInDb.getCustomerId());
//        assert(getFromDb.contains(payee));
    }


}
