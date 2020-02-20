package com.tek.trp.savingsAccount.SavingsAccountService.DAO;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByCustomerId(int customerId);

    List<Transaction> findByPayeeId(int payeeId);

    @Query(value = "SELECT * FROM Transaction WHERE customer_id = ?1 and payee_id = ?2", nativeQuery = true)
    List<Transaction> findByCustomerIdAndPayeeId(int customerId, int payeeId);

    @Query(value = "SELECT * FROM Transaction WHERE customer_id= :customerId and transaction_time between :startDate  and :endDate", nativeQuery = true)
    List<Transaction> getViewStatement(int customerId, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT sum(transaction_amount) from Transaction where customer_id=:customerId and transaction_type=:transactionType and transaction_time between :startDate and :endDate", nativeQuery = true)
    Integer sumByDatesBetween(int customerId, String transactionType, LocalDateTime startDate, LocalDateTime endDate);

}
