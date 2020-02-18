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

    @Query(value = "SELECT t FROM transaction t WHERE t.customerId= :customerId and t.payeeId= :payeeId", nativeQuery = true)
    List<Transaction> findByCustomerIdAndPayeeId(int customerId, int payeeId);

    @Query(value = "SELECT t FROM transaction t WHERE t.customerId= :customerId and t.transactionTime between :startDate  and :endDate", nativeQuery = true)
    List<Transaction> getViewStatement(int customerId, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT sum(transactionAmount) from transaction where customerId=:customerId and transactionType=:transactionType and transactionTime between :startDate and :endDate", nativeQuery = true)
    Integer sumByDatesBetween(int customerId, String transactionType, LocalDateTime startDate, LocalDateTime endDate);

}
