package com.tek.trp.savingsaccount.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static com.tek.trp.savingsaccount.utilities.ValidatorUtils.PATTERN_CUST_ID;

@Getter
public class CreditDebitRequestDTO {
    @NotNull(message = "Please provide Start Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "Please provide End Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull(message = "Please provide CustomerID ")
    @Pattern(regexp = PATTERN_CUST_ID, message = "Invalid Customer Id Format")
    private String customerId;
}

