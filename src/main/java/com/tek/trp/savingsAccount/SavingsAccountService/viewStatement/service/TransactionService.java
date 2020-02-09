package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.service;

        import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.shared.TransactionDto;
        import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.controller.ViewStatement;
        import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementRequest;
        import com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model.ViewStatementResponse;

        import java.util.List;

public interface TransactionService {
    TransactionDto addNewTransaction(TransactionDto transactionDetail );
    List<ViewStatementResponse> getStatement(ViewStatementRequest statement);
}
