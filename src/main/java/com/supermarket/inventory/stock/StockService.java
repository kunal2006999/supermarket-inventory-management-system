package com.supermarket.inventory.stock;

import com.supermarket.inventory.product.ProductEntity;
import com.supermarket.inventory.product.ProductRepository;
import com.supermarket.inventory.product.ProductService;
import com.supermarket.inventory.stock.dtos.StockRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    public StockService(StockRepository stockRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    public void addStock(StockRequest s) {
        ProductEntity product = productRepository.findById(s.getProductId())
                .orElseThrow(() -> new ProductService.ProductNotFoundException(s.getProductId()));
        var quantity = product.getQuantityInStock() + s.getChangeQuantity();
        product.setQuantityInStock(quantity);
        productRepository.save(product);

        StockEntity entry = new StockEntity();
        entry.setChangeQuantity(s.getChangeQuantity());
        entry.setFinalQuantity(quantity);
        entry.setRemarks(s.getRemarks());
        entry.setProduct(product);
        entry.setType(StockChangeType.IN);
        stockRepository.save(entry);
    }

    public void removeStock(StockRequest s) {
        ProductEntity product = productRepository.findById(s.getProductId())
                .orElseThrow(() -> new ProductService.ProductNotFoundException(s.getProductId()));
        var quantity = product.getQuantityInStock() - s.getChangeQuantity();
        product.setQuantityInStock(quantity);
        productRepository.save(product);

        StockEntity entry = new StockEntity();
        entry.setChangeQuantity(s.getChangeQuantity());
        entry.setFinalQuantity(quantity);
        entry.setRemarks(s.getRemarks());
        entry.setProduct(product);
        entry.setType(StockChangeType.OUT);
        stockRepository.save(entry);
    }

    public List<StockEntity> getStockHistory() {
        return stockRepository.findAll();
    }

    public void recordStockChange(ProductEntity product, int changeQuantity, StockChangeType type, String remarks) {
        int finalQuantity = product.getQuantityInStock();

        StockEntity stock = new StockEntity();
        stock.setProduct(product);
        stock.setChangeQuantity(changeQuantity);
        stock.setFinalQuantity(finalQuantity); // After deduction
        stock.setType(type);
        stock.setRemarks(remarks);

        stockRepository.save(stock);
    }

}
