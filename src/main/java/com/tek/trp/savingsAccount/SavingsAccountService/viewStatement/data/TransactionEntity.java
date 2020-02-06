package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data;

import com.sun.corba.se.spi.ior.ObjectId;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transaction")
public class TransactionEntity {

    @Column(nullable = false)
    private String transactionDate;

    @Column(nullable = false)
    private Double accountNumber;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private double amount;

    @Column
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
