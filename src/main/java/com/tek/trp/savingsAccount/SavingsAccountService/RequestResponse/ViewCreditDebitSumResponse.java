package com.tek.trp.savingsAccount.SavingsAccountService.RequestResponse;

import org.springframework.stereotype.Component;

@Component
public class ViewCreditDebitSumResponse {
    private int Creditsum;
    private int Debitsum;

    public int getCreditsum() {
        return Creditsum;
    }

    public void setCreditsum(int creditsum) {
        Creditsum = creditsum;
    }

    public int getDebitsum() {
        return Debitsum;
    }

    public void setDebitsum(int debitsum) {
        Debitsum = debitsum;
    }
}
