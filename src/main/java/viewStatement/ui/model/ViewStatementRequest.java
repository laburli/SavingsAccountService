package viewStatement.ui.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViewStatementRequest {

    @NotNull(message = "Please provide Start Date")
    private LocalDate startDate;

    @NotNull(message = "Please provide End Date")
    private LocalDate endDate;

    @NotNull(message = "Please provide AccountNumber Date")
    private String accountNumber;

    @NotNull(message = "Please provide CustomerID Date")
    private String customerId;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
