package com.tek.trp.savingsAccount.SavingsAccountService.RequestResponse;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ViewCreditDebitRequest {
    @NotNull(message = "Please provide Start Date")
    private LocalDateTime startDate;
    @NotNull(message = "Please provide End Date")
    private LocalDateTime endDate;
    @NotNull(message = "Please provide CustomerID ")
    private int customerId;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

