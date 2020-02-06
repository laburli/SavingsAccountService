package com.tek.trp.savingsAccount.SavingsAccountService.DAO;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeDao extends JpaRepository<Payee, String> {

    @Query("SELECT * FROM Payee p WHERE (p.customer_id = :customerId)")
    List<Payee> findAllPayeesByCustomerId(@Param("customerId") String customerId);

}
