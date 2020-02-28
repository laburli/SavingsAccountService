package com.tek.trp.savingsaccount.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column(name = "transactionType")
    private String transactionType;
    @Column(name = "availableBalance")
    private double availableBalance;
    @Column(name = "transactionAmount")
    private double transactionAmount;
    @Column(name = "customerAccountNumber")
    private String customerAccountNumber;
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "payeeId")
    private int payeeId;
    @Column(name = "payeeName")
    private String payeeName;
    @Column(name = "transactionTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime transactionTime;

}
