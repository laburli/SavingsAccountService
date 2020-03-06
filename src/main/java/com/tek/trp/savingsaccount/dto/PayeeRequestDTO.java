package com.tek.trp.savingsaccount.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.PATTERN_CUST_ID;
import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.PATTERN_IFSC;

@Getter
@Builder
public class PayeeRequestDTO {
    @NotNull(message = "Please provide CustomerID ")
    @Pattern(regexp = PATTERN_CUST_ID, message = "Invalid Customer Id Format")
    private String customerId;
    @NotNull(message = "Payee name Can't be null")
    private String name;
    @NotNull(message = "Payee nickname Can't be null")
    private String nickName;
    @NotNull(message = "Please provide Payee Account Number ")
    private String payeeAccountNumber;
    @NotNull(message = "Please provide Payee Bank IFSC Code ")
    @Pattern(regexp = PATTERN_IFSC, message = "Invalid IFSC Code")
    private String payeeBankIFSC;
    @NotNull(message = "Please provide Payee Bank Address ")
    private String payeeBankAddress;
    @NotNull(message = "Please provide Payee Bank Name ")
    private String payeeBankName;
    @NotNull(message = "Please provide Payee Bank City ")
    private String payeeBankCity;
}
