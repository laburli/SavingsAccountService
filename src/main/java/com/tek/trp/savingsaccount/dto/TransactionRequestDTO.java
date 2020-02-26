package com.tek.trp.savingsaccount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class TransactionRequestDTO {

    private String customerId;
    private String customerAccountNumber;
    private int payeeId;
    private String transactionType;
    private double transactionAmount;

    public TransactionRequestDTO(){
    }

}
