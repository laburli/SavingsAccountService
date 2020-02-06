package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payee")
public class Payee {

    @Id
    @Column(name = "id")
    private int ID;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickName;
    @Column(name = "payee_bank_ifsc")
    private String payeeBankIFSC;
    @Column(name = "payee_bank_address")
    private String payeeBankAddress;
    @Column(name = "payee_bank_name")
    private String payeeBankName;
    @Column(name = "payee_bank_city")
    private String payeeBankCity;
    @Column(name = "account_number")
    private long accountNumber;

    public int getID() {
        return ID;
    }

    //Just for initiation purpose
    public Payee(int ID, int customerId, String name, long accountNumber) {
        this.ID = ID;
        this.customerId = customerId;
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }
}
