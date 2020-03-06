package com.tek.trp.savingsaccount.repository;

import com.tek.trp.savingsaccount.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByCustomerId(String customerId);

    List<Transaction> findByPayeeId(int payeeId);

    @Query(value = "SELECT * FROM Transaction WHERE customer_id = ?1 and payee_id = ?2", nativeQuery = true)
    List<Transaction> findByCustomerIdAndPayeeId(String customerId, int payeeId);

    @Query(value = "SELECT available_balance FROM Transaction WHERE customer_account_number=:accountNum ORDER BY transaction_time DESC LIMIT 1", nativeQuery = true)
    Double getBalance(String accountNum);

    @Query(value = "SELECT sum(transaction_amount) from Transaction where customer_id=:customerId and upper(transaction_type)=:transactionType and transaction_time between :startDate and :endDate ORDER BY transaction_time", nativeQuery = true)
    Integer sumByDatesBetween(String customerId, String transactionType, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM Transaction WHERE customer_id= :customerId and transaction_time between :startDate  and :endDate ORDER BY transaction_time", nativeQuery = true)
    List<Transaction> viewStatement(String customerId, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM Transaction WHERE customer_id= :customerId and customer_account_number=:accNum and transaction_time between :startDate  and :endDate ORDER BY transaction_time", nativeQuery = true)
    List<Transaction> viewStatementByAccount(String customerId, String accNum, LocalDate startDate, LocalDate endDate);

}
