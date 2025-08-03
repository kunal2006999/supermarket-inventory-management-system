package com.supermarket.inventory.product.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
public class CreateProductResponse {

    private Long id;

    private String name;

    private String category;

    private String brand;

    private BigDecimal pricePerUnit;

    private int quantityInStock;

    private int reorderLevel;

    private boolean isDeleted = false;

    private Long SupplierId;
}
