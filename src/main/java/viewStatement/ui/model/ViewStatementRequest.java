package viewStatement.ui.model;

import javax.validation.constraints.NotNull;

public class ViewStatementRequest {

    @NotNull(message = "Please provide Start Date")
    private String startDate;

    @NotNull(message = "Please provide End Date")
    private String endDate;

    @NotNull(message = "Please provide AccountNumber Date")
    private String accountNumber;

    @NotNull(message = "Please provide CustomerID Date")
    private String customerId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
