package com.tek.trp.savingsAccount.SavingsAccountService.DAO;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, String> {

    @Query("SELECT * FROM Transaction t WHERE (t.customer_id = :customerId)")
    List<Transaction> findAllTransactionsByCustomerId(@Param("customerId") String customerId);
}
