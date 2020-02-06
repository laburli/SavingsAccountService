package com.tek.trp.savingsAccount.SavingsAccountService.DAO;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, String> {

}
