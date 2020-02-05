package creditdebitsum.Model;
import javax.validation.constraints.NotNull;
public class Request {
    @NotNull(message = "Please provide Start Date")
    private String startDate;
    @NotNull(message = "Please provide End Date")
    private String endDate;
    @NotNull(message = "Please provide CustomerID ")
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

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}