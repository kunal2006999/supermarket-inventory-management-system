package com.supermarket.inventory.product.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@Setter(AccessLevel.NONE)
public class UpdateProductRequest {

    private String name;

    private String category;

    private String brand;

    private BigDecimal pricePerUnit;

    private int reorderLevel;

    private boolean isDeleted = false;

    /*
    @NonNull
    private Long SupplierId;
    */
}
