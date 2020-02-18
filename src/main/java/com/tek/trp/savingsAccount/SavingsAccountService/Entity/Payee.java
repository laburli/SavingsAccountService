package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.*;

@Entity
@Table(name = "payee")
public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PayeeID")
    private int payeeId;
    @Column(name = "customerId")
    private int customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "payeeBankIFSC", length = 11)
    private String payeeBankIFSC;
    @Column(name = "payeeBankAddress")
    private String payeeBankAddress;
    @Column(name = "payeeBankName")
    private String payeeBankName;
    @Column(name = "payeeBankCity")
    private String payeeBankCity;
    @Column(name = "accountNumber")
    private long accountNumber;

    public Payee() {
    }

    public Payee(int payeeId, int customerId, String name, String nickName, String payeeBankIFSC, String payeeBankAddress, String payeeBankName, String payeeBankCity, long accountNumber) {
        this.payeeId = payeeId;
        this.customerId = customerId;
        this.name = name;
        this.nickName = nickName;
        this.payeeBankIFSC = payeeBankIFSC;
        this.payeeBankAddress = payeeBankAddress;
        this.payeeBankName = payeeBankName;
        this.payeeBankCity = payeeBankCity;
        this.accountNumber = accountNumber;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPayeeBankIFSC() {
        return payeeBankIFSC;
    }

    public String getPayeeBankAddress() {
        return payeeBankAddress;
    }

    public String getPayeeBankName() {
        return payeeBankName;
    }

    public String getPayeeBankCity() {
        return payeeBankCity;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
}
