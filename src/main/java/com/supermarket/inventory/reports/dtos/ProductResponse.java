package com.supermarket.inventory.reports.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String name;

    private int unitsSold;

    private BigDecimal pricePerUnit;

    private BigDecimal totalRevenue;
}
