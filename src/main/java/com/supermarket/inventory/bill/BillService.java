package com.supermarket.inventory.bill;

import com.supermarket.inventory.bill.dtos.CreateBillRequest;
import com.supermarket.inventory.product.ProductEntity;
import com.supermarket.inventory.product.ProductRepository;
import com.supermarket.inventory.product.dtos.CreateProductRequest;
import com.supermarket.inventory.users.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    public BillService(BillRepository billRepository, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
    }

    public BillEntity createBill(CreateBillRequest b) {
        BillEntity newBill = modelMapper.map(b, BillEntity.class);

        return billRepository.save(newBill);
    }

    public BillEntity getBillById(Long BillId) {
        return billRepository.findById(BillId).orElseThrow(() -> new BillService.BillNotFoundException(BillId));
    }

    public List<BillEntity> getAllBills() {
        return billRepository.findAll();
    }

    public static class BillNotFoundException extends IllegalArgumentException {
        public BillNotFoundException(Long BillId) {
            super("Bill with id" + BillId + " not found");
        }
    }

}
