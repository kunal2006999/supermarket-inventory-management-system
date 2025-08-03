package com.supermarket.inventory.bill;

import com.supermarket.inventory.bill.dtos.CreateBillRequest;
import com.supermarket.inventory.bill.dtos.CreateBillResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;
    private final ModelMapper modelMapper;

    public BillController(BillService billService, ModelMapper modelMapper) {
        this.billService = billService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<CreateBillResponse> generateBill(@RequestBody CreateBillRequest request) {
        CreateBillResponse response = billService.createBill(request);
        URI savedBillUri = URI.create("/products/" + response.getId());
        return ResponseEntity.created(savedBillUri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateBillResponse> getBillById(@PathVariable("id") Long id) {
        CreateBillResponse response = billService.getBillById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<CreateBillResponse>> getAllBills() {
        List<CreateBillResponse> response = billService.getAllBills();
        return ResponseEntity.ok(response);
    }


}
