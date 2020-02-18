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

    @Query(value = "SELECT t FROM transaction t WHERE t.customerId= :customerId and t.transactionTime between :startDate  and :endDate")
    List<Transaction> getViewStatement(int customerId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT sum(transactionAmount) from transaction where customerId=:customerId and transactionType=:transactiontype and transactionTime between :start and :end")
    Integer sumByDatesBetween(int customerId, LocalDateTime start, LocalDateTime end, String transactiontype);

}
