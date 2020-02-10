package com.tek.trp.savingsAccount.SavingsAccountService.DAO;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
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

    Payee getMockPayee() {
        return new Payee(1235, 5433, "Kashish Mehta", "kashish", "", "", "", "", 8777777L);
    }

    @Test
    public void testSavePayee() {
        Payee payee = getMockPayee();
        Payee savedInDb = testEntityManager.persist(payee);
        Optional<Payee> getFromDb = payeeDao.findById(savedInDb.getPayeeId());

        if (getFromDb.isPresent())
            assertEquals(getFromDb.get(), payee);

    }

    @Test
    public void testFindByCustomerId() {
        Payee payee = getMockPayee();
        //Payee savedInDb = testEntityManager.persist(payee);
        //List<Payee> getFromDb = payeeDao.findByCustomerId(savedInDb.getCustomerId());
        //assert(getFromDb.contains(payee));
    }


}
