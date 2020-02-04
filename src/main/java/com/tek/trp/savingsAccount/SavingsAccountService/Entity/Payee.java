package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payee {

    @Id
    @GeneratedValue
    private int ID;
    private int customerId;
    private String name;
    private String nickName;
    private String payeeBankIFSC;
    private String payeeBankAddress;
    private String payeeBankName;
    private String payeeBankCity;
    private long accountNumber;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    public String getPayeeBankCity() {
        return payeeBankCity;
    }

    public void setPayeeBankCity(String payeeBankCity) {
        this.payeeBankCity = payeeBankCity;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
