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
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "customerBranch")
    private String customerBranch;
    @Column(name = "customerBank")
    private String customerBank;
    @Column(name = "customerIFSC")
    private String customerIFSC;
    @Column(name = "customerBankAddress")
    private String customerBankAddress;
    @Column(name = "payeeName")
    private String payeeName;
    @Column(name = "payeeId")
    private int payeeId;
    @Column(name = "payeeAccountNumber")
    private long payeeAccountNumber;
    @Column(name = "payeeBankName")
    private String payeeBankName;
    @Column(name = "payeeBankIFSC")
    private String payeeBankIFSC;
    @Column(name = "payeeBankAddress")
    private String payeeBankAddress;
    @Column(name = "transactionTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime transactionTime;

    public Transaction() {

    }

    public Transaction(int transactionId, String transactionType, double availableBalance, double transactionAmount, long customerAccountNumber, int customerId, String customerName, String customerBranch, String customerBank, String customerIFSC, String customerBankAddress, String payeeName, int payeeId, long payeeAccountNumber, String payeeBankName, String payeeBankIFSC, String payeeBankAddress, LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.availableBalance = availableBalance;
        this.transactionAmount = transactionAmount;
        this.customerAccountNumber = customerAccountNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerBranch = customerBranch;
        this.customerBank = customerBank;
        this.customerIFSC = customerIFSC;
        this.customerBankAddress = customerBankAddress;
        this.payeeName = payeeName;
        this.payeeId = payeeId;
        this.payeeAccountNumber = payeeAccountNumber;
        this.payeeBankName = payeeBankName;
        this.payeeBankIFSC = payeeBankIFSC;
        this.payeeBankAddress = payeeBankAddress;
        this.transactionTime = transactionTime;
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

    public int getCustomerId() {
        return customerId;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public long getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerBranch() {
        return customerBranch;
    }

    public String getCustomerBank() {
        return customerBank;
    }

    public String getCustomerIFSC() {
        return customerIFSC;
    }

    public String getCustomerBankAddress() {
        return customerBankAddress;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public String getPayeeBankIFSC() {
        return payeeBankIFSC;
    }

    public String getPayeeBankAddress() {
        return payeeBankAddress;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
}
