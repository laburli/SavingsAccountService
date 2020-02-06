package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViewStatementRequest {

    @NotNull(message = "Please provide Start Date")
    private String startDate;

    @NotNull(message = "Please provide End Date")
    private String endDate;

    @NotNull(message = "Please provide AccountNumber Date")
    private Double accountNumber;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Double accountNumber) {
        this.accountNumber = accountNumber;
    }

}
