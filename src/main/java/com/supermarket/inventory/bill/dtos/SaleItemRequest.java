package com.supermarket.inventory.bill.dtos;

import com.supermarket.inventory.bill.BillEntity;
import com.supermarket.inventory.product.ProductEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class SaleItemRequest {

    @NonNull
    private long productId;

    @NonNull
    private int quantity;
}
