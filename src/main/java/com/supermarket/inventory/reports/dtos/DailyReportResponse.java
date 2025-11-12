package com.supermarket.inventory.reports.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DailyReportResponse {

    private LocalDateTime date;

    private BigDecimal totalAmount;

    private int totalTransactions;

    private BigDecimal averageBillAmount;

    private int totalItemSold;

    private float highestBillAmount;

}
