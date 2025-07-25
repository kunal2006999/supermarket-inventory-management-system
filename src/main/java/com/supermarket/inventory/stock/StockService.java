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
        ProductEntity product = productRepository.findById(s.getProduct().getId())
                .orElseThrow(() -> new ProductService.ProductNotFoundException(s.getProduct().getId()));
        product.setQuantityInStock(product.getQuantityInStock() + s.getChangeQuantity());
        productRepository.save(product);

        StockEntity entry = modelMapper.map(s, StockEntity.class);
        stockRepository.save(entry);
    }

    public void removeStock(StockRequest s) {
        ProductEntity product = productRepository.findById(s.getProduct().getId())
                .orElseThrow(() -> new ProductService.ProductNotFoundException(s.getProduct().getId()));
        product.setQuantityInStock(product.getQuantityInStock() - s.getChangeQuantity());
        productRepository.save(product);

        StockEntity entry = modelMapper.map(s, StockEntity.class);
        stockRepository.save(entry);
    }

    public List<StockEntity> getStockHistory() {
        return stockRepository.findAll();
    }

}
