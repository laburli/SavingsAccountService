package com.tek.trp.savingsaccount.repository;

import com.tek.trp.savingsaccount.entity.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeDao extends JpaRepository<Payee, Integer> {

    List<Payee> findByCustomerId(String customerId);

    @Query(value = "SELECT * FROM Payee WHERE customer_id = ?1 and payee_account_number = ?2", nativeQuery = true)
    List<Payee> findByCustomerIdAndPayeeAccountNumber(String customerId, String payeeAccountNumber);
}
