package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private String transactionType;
    private double availableBalance;
    private double transactionAmount;
    private long customerAccountNumber;
    private int customerId;
    private String customerName;
    private String customerBranch;
    private String customerBank;
    private String customerIFSC;
    private String customerBankAddress;
    private String payeeName;
    private int payeeId;
    private long payeeAccountNumber;
    private String payeeBankName;
    private String payeeBankIFSC;
    private String payeeBankAddress;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionTime;

    public int getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
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
}
