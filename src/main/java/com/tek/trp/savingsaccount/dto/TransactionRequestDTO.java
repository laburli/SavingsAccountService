package com.tek.trp.savingsaccount.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.patternAccNum;
import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.patternCustomerId;

@Getter
@Setter
public class TransactionRequestDTO {

    @NotNull(message = "Please provide Customer ID ")
    @Pattern(regexp = patternCustomerId, message = "Invalid Customer Id Format")
    private String customerId;
    @NotNull(message = "Please provide Customer Account Number")
    @Pattern(regexp = patternAccNum, message = "Invalid Account Number")
    private String customerAccountNumber;
    @NotNull(message = "Please provide Payee ID ")
    private int payeeId;
    @NotNull(message = "Transaction Type can't be null ")
    private String transactionType;
    @NotNull(message = "Transaction Amount can't be null")
    private double transactionAmount;

}
