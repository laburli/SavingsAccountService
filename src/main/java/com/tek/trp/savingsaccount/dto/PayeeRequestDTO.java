package com.tek.trp.savingsaccount.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayeeRequestDTO {
    private String customerId;
    private String name;
    private String nickName;
    private String payeeAccountNumber;
    private String payeeBankIFSC;
    private String payeeBankAddress;
    private String payeeBankName;
    private String payeeBankCity;
}
