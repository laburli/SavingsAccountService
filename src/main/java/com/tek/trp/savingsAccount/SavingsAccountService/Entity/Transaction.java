package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {

    @Id
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
    private Date transactionTime;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(long customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBranch() {
        return customerBranch;
    }

    public void setCustomerBranch(String customerBranch) {
        this.customerBranch = customerBranch;
    }

    public String getCustomerBank() {
        return customerBank;
    }

    public void setCustomerBank(String customerBank) {
        this.customerBank = customerBank;
    }

    public String getCustomerIFSC() {
        return customerIFSC;
    }

    public void setCustomerIFSC(String customerIFSC) {
        this.customerIFSC = customerIFSC;
    }

    public String getCustomerBankAddress() {
        return customerBankAddress;
    }

    public void setCustomerBankAddress(String customerBankAddress) {
        this.customerBankAddress = customerBankAddress;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(int payeeId) {
        this.payeeId = payeeId;
    }

    public long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(long payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    public String getPayeeBankIFSC() {
        return payeeBankIFSC;
    }

    public void setPayeeBankIFSC(String payeeBankIFSC) {
        this.payeeBankIFSC = payeeBankIFSC;
    }

    public String getPayeeBankAddress() {
        return payeeBankAddress;
    }

    public void setPayeeBankAddress(String payeeBankAddress) {
        this.payeeBankAddress = payeeBankAddress;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
