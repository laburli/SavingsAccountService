package com.tek.trp.savingsAccount.SavingsAccountService.viewStatement.data;

import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="transaction")
public class TransactionEntity {

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private Double accountNumber;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private double amount;

    @Column
    private String location;


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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
