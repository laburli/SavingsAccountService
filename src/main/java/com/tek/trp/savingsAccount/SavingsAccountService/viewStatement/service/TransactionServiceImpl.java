package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.service;

import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementRequest;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data.TransactionEntity;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data.TransactionRepository;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.shared.TransactionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ViewStatementResponse> getStatement(ViewStatementRequest statement) {
        ViewStatementResponse response =new ViewStatementResponse();
        Double account= statement.getAccountNumber();
        LocalDateTime startDate=statement.getStartDate();
        LocalDateTime endDate=statement.getEndDate();
        TransactionEntity entity =new TransactionEntity();
        List<TransactionEntity> entityList =new ArrayList();
        entityList= transactionRepository.getViewStatement(account,startDate,endDate);


//        entity = entityList.get(0);
//        response.setAmount(entity.getAmount());
//        response.setLocation(entity.getLocation());
//        response.setTransactionDate(entity.getTransactionDate());

        List<ViewStatementResponse> responseList = new ArrayList<>();
        entityList.forEach(x-> responseList.add(new ViewStatementResponse(x.getLocation(),x.getAmount(),x.getTransactionDate())));
        return responseList;
    }
}
