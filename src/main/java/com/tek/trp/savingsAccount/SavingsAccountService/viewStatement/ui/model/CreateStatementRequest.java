package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.ui.model;

import javax.validation.constraints.NotNull;

public class CreateStatementRequest
{

    @NotNull(message = "Please provide Start Date")
    private String transactionDate;

    @NotNull(message = "Please provide AccountNumber Date")
    private Double accountNumber;

    private double amount;

    private String location;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
