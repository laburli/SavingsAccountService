package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.*;

@Entity
@Table(name = "payee")
public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PayeeID")
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

    public Payee() {
    }

    public Payee(int ID, int customerId, String name, String nickName, String payeeBankIFSC, String payeeBankAddress, String payeeBankName, String payeeBankCity, long accountNumber) {
        this.ID = ID;
        this.customerId = customerId;
        this.name = name;
        this.nickName = nickName;
        this.payeeBankIFSC = payeeBankIFSC;
        this.payeeBankAddress = payeeBankAddress;
        this.payeeBankName = payeeBankName;
        this.payeeBankCity = payeeBankCity;
        this.accountNumber = accountNumber;
    }


    public int getCustomerId() {
        return customerId;
    }
}
