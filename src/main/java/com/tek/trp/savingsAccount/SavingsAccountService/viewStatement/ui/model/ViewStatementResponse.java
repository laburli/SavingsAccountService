package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model;

import java.time.LocalDate;

public class ViewStatementResponse {
    private String location;
    private double amount;
    private String transactionDate;

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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
