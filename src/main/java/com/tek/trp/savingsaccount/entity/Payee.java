package com.tek.trp.savingsaccount.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payee")
@Data
public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PayeeID")
    private int payeeId;
    @Column(name = "payeeStatus")
    private String payeeStatus;
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "payeeAccountNumber")
    private String payeeAccountNumber;
    @Column(name = "payeeBankIFSC", length = 11)
    private String payeeBankIFSC;
    @Column(name = "payeeBankAddress")
    private String payeeBankAddress;
    @Column(name = "payeeBankName")
    private String payeeBankName;
    @Column(name = "payeeBankCity")
    private String payeeBankCity;

}
