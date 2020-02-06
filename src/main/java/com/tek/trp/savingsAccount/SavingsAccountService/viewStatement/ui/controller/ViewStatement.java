package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.controller;

import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.service.TransactionService;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.shared.TransactionDto;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.CreateStatementRequest;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementRequest;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("user")
public class ViewStatement {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/createStatement")
    public ResponseEntity createStatement(@RequestBody CreateStatementRequest createStatementRequest){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if(createStatementRequest.getAmount()==0)
        {
            Random rand = new Random();

            // Generate random integers in range 0 to 999
            createStatementRequest.setAmount( rand.nextInt(1000));
        }

        if(createStatementRequest.getLocation().length()== 0 || createStatementRequest.getLocation()==null)
        {
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb = new StringBuilder(20);
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);}
                createStatementRequest.setLocation(sb.toString());
        }
        TransactionDto transactionDto = modelMapper.map(createStatementRequest,TransactionDto.class);
        transactionService.addNewTransaction(transactionDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/viewStatement")
    public ResponseEntity<ViewStatementResponse[]> viewStatements(@RequestBody ViewStatementRequest viewStatementRequest){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
