package com.supermarket.inventory.stock.dtos;

import com.supermarket.inventory.product.ProductEntity;
import com.supermarket.inventory.stock.StockChangeType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
public class StockRequest {

    @NonNull
    private ProductEntity product;

    private int changeQuantity;

    private String remarks;

}
