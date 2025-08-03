package com.supermarket.inventory.stock;

import com.supermarket.inventory.product.dtos.CreateProductResponse;
import com.supermarket.inventory.stock.dtos.ProductResponse;
import com.supermarket.inventory.stock.dtos.StockRequest;
import com.supermarket.inventory.stock.dtos.StockResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    private final ModelMapper modelMapper;

    public StockController(StockService stockService, ModelMapper modelMapper) {
        this.stockService = stockService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    ResponseEntity<String> addStock(@RequestBody StockRequest request) {
        stockService.addStock(request);
        return ResponseEntity.ok("Stock added successfully.");
    }

    @PostMapping("/remove")
    ResponseEntity<String> removeStock(@RequestBody StockRequest request) {
        stockService.removeStock(request);
        return ResponseEntity.ok("Stock reduce successfully.");
    }

    @GetMapping("/history")
    ResponseEntity<List<StockResponse>> getStockHistory() {
        var stocks = stockService.getStockHistory();
        List<StockResponse> stockResponse = stocks.stream()
                .map(stock -> {
                    StockResponse response = modelMapper.map(stock, StockResponse.class);
                    response.setProduct(modelMapper.map(stock.getProduct(), ProductResponse.class));
                    return response;
                })
                .collect(Collectors.toList());
        Collections.reverse(stockResponse);
        return ResponseEntity.ok(stockResponse);
    }

}
