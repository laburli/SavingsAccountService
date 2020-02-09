package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "SELECT t FROM TransactionEntity t WHERE t.accountNumber= :accountNumber and t.transactionDate between :startDate  and :endDate")
    public List<TransactionEntity> getViewStatement(@Param("accountNumber") Double account_number, @Param("startDate") LocalDateTime startDate , @Param("endDate") LocalDateTime endDate);
}
