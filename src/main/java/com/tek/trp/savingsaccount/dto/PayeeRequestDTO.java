package com.tek.trp.savingsaccount.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.*;

@Getter
@Builder
public class PayeeRequestDTO {
    @NotNull(message = "Please provide CustomerID ")
    @Pattern(regexp = patternCustomerId, message = "Invalid Customer Id Format")
    private String customerId;
    @NotNull(message = "Payee name Can't be null")
    private String name;
    @NotNull(message = "Payee nickname Can't be null")
    private String nickName;
    @NotNull(message = "Please provide Payee Account Number ")
    @Pattern(regexp = patternAccNum, message = "Invalid Account Number")
    private String payeeAccountNumber;
    @NotNull(message = "Please provide Payee Bank IFSC Code ")
    @Pattern(regexp = patternIFSC, message = "Invalid IFSC Code")
    private String payeeBankIFSC;
    @NotNull(message = "Please provide Payee Bank Address ")
    private String payeeBankAddress;
    @NotNull(message = "Please provide Payee Bank Name ")
    private String payeeBankName;
    @NotNull(message = "Please provide Payee Bank City ")
    private String payeeBankCity;
}
