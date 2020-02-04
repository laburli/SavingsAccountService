package com.tek.trp.savingsAccount.SavingsAccountService.Repo;

import com.tek.trp.savingsAccount.SavingsAccountService.Entity.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayeeRepo extends JpaRepository<Payee, String> {

}
