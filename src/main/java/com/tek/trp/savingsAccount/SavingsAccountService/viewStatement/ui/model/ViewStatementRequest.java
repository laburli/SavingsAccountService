package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViewStatementRequest {

    @NotNull(message = "Please provide Start Date")
    private LocalDateTime startDate;

    @NotNull(message = "Please provide End Date")
    private LocalDateTime endDate;

    @NotNull(message = "Please provide AccountNumber Date")
    private Double accountNumber;

    public Double getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ViewStatementRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
