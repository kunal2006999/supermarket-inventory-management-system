package com.supermarket.inventory.bill;

import com.supermarket.inventory.bill.dtos.*;
import com.supermarket.inventory.product.ProductEntity;
import com.supermarket.inventory.product.ProductRepository;
import com.supermarket.inventory.product.dtos.CreateProductRequest;
import com.supermarket.inventory.product.dtos.CreateProductResponse;
import com.supermarket.inventory.stock.StockChangeType;
import com.supermarket.inventory.stock.StockEntity;
import com.supermarket.inventory.stock.StockRepository;
import com.supermarket.inventory.stock.StockService;
import com.supermarket.inventory.users.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService implements BillServiceInterface {

    private final BillRepository billRepository;
    private final ModelMapper modelMapper;
    private final SaleItemRepository saleItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockService stockService;

    public BillService(BillRepository billRepository, ModelMapper modelMapper, SaleItemRepository saleItemRepository) {
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
        this.saleItemRepository = saleItemRepository;
    }

    public CreateBillResponse createBill(CreateBillRequest b) {
        List<SaleItemRequest> itemRequests = b.getSaleItems();
        List<SaleItemEntity> saleItems = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.valueOf(0.00);

        for(SaleItemRequest itemRequest: itemRequests) {

            Long productId = itemRequest.getProductId();
            int quantity = itemRequest.getQuantity();

            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found: ID " + productId));

            int availableQty = product.getQuantityInStock();
            if (availableQty < quantity) {
                throw new RuntimeException("Insufficient stock for product ID " + productId);
            }

            product.setQuantityInStock(availableQty - quantity);
            productRepository.save(product);

            stockService.recordStockChange(product, -quantity, StockChangeType.OUT, "Sale of " + quantity + " units"
            );


            BigDecimal itemTotal = product.getPricePerUnit().multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(itemTotal);


            stockService.recordStockChange(
                    product,
                    -quantity,
                    StockChangeType.OUT,
                    "Sale of " + quantity + " units"
            );



            SaleItemEntity saleItem = new SaleItemEntity();
            saleItem.setProduct(product);
            saleItem.setQuantity(quantity);
            saleItem.setUnitPrice(product.getPricePerUnit());
            saleItem.setTotal(itemTotal);

            saleItems.add(saleItem);

        }

        BillEntity bill = new BillEntity();
        bill.setCustomerName(b.getCustomerName());
        bill.setPaymentMode(b.getPaymentMode());
        bill.setSaleDateTime(LocalDateTime.now());
        bill.setTotalAmount(totalAmount);
        bill = billRepository.save(bill);

        for (SaleItemEntity item : saleItems) {
            item.setBill(bill);
            saleItemRepository.save(item);
        }


        List<SaleItemsResponse> itemResponses = saleItems.stream()
                .map(item -> new SaleItemsResponse(
                        item.getId(),
                        new ProductResponse(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPricePerUnit()
                        ),
                        item.getQuantity()
                ))
                .toList();


        return new CreateBillResponse(
                bill.getId(),
                bill.getSaleDateTime(),
                bill.getCustomerName(),
                bill.getPaymentMode(),
                bill.getTotalAmount(),
                itemResponses
        );
    }

    public CreateBillResponse getBillById(Long BillId) {
        BillEntity bill = billRepository.findById(BillId)
                .orElseThrow(() -> new BillNotFoundException(BillId));

        return mapToBillResponse(bill);
    }

    public List<CreateBillResponse> getAllBills() {
        List<BillEntity> bills = billRepository.findAll();
        return bills.stream()
                .map(this::mapToBillResponse)
                .toList();
    }

    public static class BillNotFoundException extends IllegalArgumentException {
        public BillNotFoundException(Long BillId) {
            super("Bill with id" + BillId + " not found");
        }
    }

    private CreateBillResponse mapToBillResponse(BillEntity bill) {
        List<SaleItemsResponse> itemResponses = bill.getSaleItems().stream()
                .map(item -> new SaleItemsResponse(
                        item.getId(),
                        new ProductResponse(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPricePerUnit()
                        ),
                        item.getQuantity()
                ))
                .toList();

        return new CreateBillResponse(
                bill.getId(),
                bill.getSaleDateTime(),
                bill.getCustomerName(),
                bill.getPaymentMode(),
                bill.getTotalAmount(),
                itemResponses
        );
    }

}
