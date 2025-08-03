package com.supermarket.inventory.bill.dtos;

import com.supermarket.inventory.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemsResponse {

    private long id;

    private ProductResponse product;

    private int quantity;

}
