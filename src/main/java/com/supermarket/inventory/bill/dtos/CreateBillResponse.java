package com.supermarket.inventory.bill.dtos;

import com.supermarket.inventory.bill.SaleItemEntity;
import com.supermarket.inventory.users.UserEntity;
import com.supermarket.inventory.users.dtos.CreateUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillResponse {

    private Long id;

    private LocalDateTime saleDateTime;

    private String customerName;

    private String paymentMode;

    private BigDecimal totalAmount;

    //private CreateUserResponse staff;

    private List<SaleItemsResponse> saleItems;

}
