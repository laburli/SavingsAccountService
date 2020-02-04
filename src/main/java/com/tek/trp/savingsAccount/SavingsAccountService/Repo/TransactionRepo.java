package com.tek.trp.savingsAccount.SavingsAccountService.Repo;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, String> {
}
