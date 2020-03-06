package com.tek.trp.savingsaccount.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.*;

@Getter
@Setter
public class TransactionRequestDTO {

    @NotNull(message = "Please provide Customer ID ")
    @Pattern(regexp = PATTERN_CUST_ID, message = "Invalid Customer Id Format")
    private String customerId;
    @NotNull(message = "Please provide Customer Account Number")
    @Pattern(regexp = PATTERN_ACC_NUM, message = "Invalid Account Number")
    private String customerAccountNumber;
    @NotNull(message = "Please provide Payee ID ")
    private int payeeId;
    @NotNull(message = "Transaction Type can't be null ")
    @Pattern(regexp = PATTERN_TRANS_TYPE, message = "Please Enter Correct Transaction Type")
    private String transactionType;
    @NotNull(message = "Transaction Amount can't be null")
    private double transactionAmount;

}
