package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column(name = "transactionType")
    private String transactionType;
    @Column(name = "availableBalance")
    private double availableBalance;
    @Column(name = "transactionAmount")
    private double transactionAmount;
    @Column(name = "customerAccountNumber")
    private long customerAccountNumber;
    @Column(name = "customerId")
    private int customerId;
    @Column(name = "payeeId")
    private int payeeId;
    @Column(name = "payeeName")
    private String payeeName;
    @Column(name = "payeeAccountNumber")
    private long payeeAccountNumber;
    @Column(name = "transactionTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime transactionTime;

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public long getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
}
