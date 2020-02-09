package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViewStatementResponse {

    private String location;
    private double amount;
    private LocalDateTime transactionDate;

    public ViewStatementResponse() {
    }

    public ViewStatementResponse(String location, double amount, LocalDateTime transactionDate) {
        this.location = location;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
