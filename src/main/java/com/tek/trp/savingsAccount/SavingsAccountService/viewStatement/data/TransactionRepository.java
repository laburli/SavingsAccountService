package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

}
