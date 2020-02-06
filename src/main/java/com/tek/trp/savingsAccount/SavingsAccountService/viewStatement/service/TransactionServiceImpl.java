package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data.TransactionEntity;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data.TransactionRepository;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.shared.TransactionDto;

@Service
public class TransactionServiceImpl implements TransactionService {


    TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }

    @Override
    public TransactionDto addNewTransaction(TransactionDto transactionDetail) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TransactionEntity transactionEntity=modelMapper.map(transactionDetail, TransactionEntity.class);
        transactionRepository.save(transactionEntity);

        return null;
    }
}
