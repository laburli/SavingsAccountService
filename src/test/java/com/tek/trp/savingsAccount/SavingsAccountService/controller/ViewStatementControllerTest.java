package com.tek.trp.savingsAccount.SavingsAccountService.controller;

import static javafx.scene.input.KeyCode.T;
import static org.mockito.ArgumentMatchers.any;

import com.tek.trp.savingsAccount.SavingsAccountService.SavingsAccountServiceApplication;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.service.TransactionService;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.controller.ViewStatement;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementRequest;
import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SavingsAccountServiceApplication.class)
public class ViewStatementControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @InjectMocks
    private ViewStatement viewStatement;

    @Mock
    private TransactionService transactionService;

    @Test
    public void verifyViewStatement() throws Exception {
        List<ViewStatementResponse> viewStatementRequests = Arrays.asList(getStatement());
        Mockito.when(transactionService.getStatement(any(ViewStatementRequest.class))).thenReturn(viewStatementRequests);
        //viewStatement.viewStatements(getStatement());
    }

    private ViewStatementResponse getStatement() {
        ViewStatementResponse viewStatementResponse = new ViewStatementResponse();
        viewStatementResponse.setAmount(10.0);
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(5, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        viewStatementResponse.setTransactionDate(dateTime);
        viewStatementResponse.setLocation("sadfsh");
        return viewStatementResponse;
    }
}
