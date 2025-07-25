package com.supermarket.inventory.bill.dtos;

import com.supermarket.inventory.bill.SaleItemEntity;
import com.supermarket.inventory.users.UserEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class CreateBillRequest {

    @NonNull
    private String customerName;

    @NonNull
    private String paymentMode;

    @NonNull
    private UserEntity staff;

    @NonNull
    private List<SaleItemEntity> saleItems = new ArrayList<>();

}
