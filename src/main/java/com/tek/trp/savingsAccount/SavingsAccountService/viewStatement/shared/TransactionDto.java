package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.shared;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDto {
    private LocalDateTime transactionDate;

    @NotNull(message = "Please provide AccountNumber Date")
    private Double accountNumber;

    @NotNull(message = "Please provide End Date")
    private double amount;

    @NotNull(message = "Please provide End Date")
    private String location;

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
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
