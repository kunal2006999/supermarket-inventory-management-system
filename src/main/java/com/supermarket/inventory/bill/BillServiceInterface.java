package com.supermarket.inventory.bill;

import com.supermarket.inventory.bill.dtos.CreateBillRequest;
import com.supermarket.inventory.bill.dtos.CreateBillResponse;

import java.util.List;

public interface BillServiceInterface {

    CreateBillResponse createBill(CreateBillRequest request);
    CreateBillResponse getBillById(Long billId);
    List<CreateBillResponse> getAllBills();
}
