package com.tek.trp.savingsAccount.SavingsAccountService.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private int ID;
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

}
