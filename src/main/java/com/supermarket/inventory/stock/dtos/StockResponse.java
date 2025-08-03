package com.supermarket.inventory.stock.dtos;

import com.supermarket.inventory.product.ProductEntity;
import com.supermarket.inventory.stock.StockChangeType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockResponse {

    private ProductResponse product;

    private Long id;

    private int changeQuantity;

    private int finalQuantity;

    private StockChangeType type;

    private LocalDateTime timestamp;

    private String remarks;

}
