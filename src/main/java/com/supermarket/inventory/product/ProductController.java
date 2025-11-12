package com.supermarket.inventory.product;

import com.supermarket.inventory.product.dtos.CreateProductRequest;
import com.supermarket.inventory.product.dtos.CreateProductResponse;
import com.supermarket.inventory.product.dtos.UpdateProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    ResponseEntity<CreateProductResponse> addProduct(@RequestBody CreateProductRequest request) {
        ProductEntity savedProduct = productService.createProduct(request);
        URI savedProductUri = URI.create("/products/" + savedProduct.getId());

        return ResponseEntity.created(savedProductUri).body(modelMapper.map(savedProduct, CreateProductResponse.class));
    }

    @PatchMapping("/{id}")
    ResponseEntity<CreateProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequest request) {
        var updatedProduct = productService.updateProduct(id, request);
        var productResponse = modelMapper.map(updatedProduct, CreateProductResponse.class);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("product deleted successfully");
    }


    @GetMapping("/{id}")
    ResponseEntity<CreateProductResponse> getProduct(@PathVariable("id") Long id) {
        var product = productService.getProduct(id);
        var productResponse = modelMapper.map(product, CreateProductResponse.class);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("")
    ResponseEntity<List<CreateProductResponse>> getAllProduct() {
        var products = productService.getAllProduct();
        List<CreateProductResponse> productResponse = products.stream()
                .map(product -> modelMapper.map(product, CreateProductResponse.class))
                .toList();
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/search")
    ResponseEntity<List<CreateProductResponse>> searchProducts(@RequestParam(required = false) String name,
                                                               @RequestParam(required = false) String category,
                                                               @RequestParam(required = false) String brand) {
        var products = productService.searchProducts(name, category, brand);
        List<CreateProductResponse> productResponse = products.stream()
                .map(product -> modelMapper.map(product, CreateProductResponse.class))
                .toList();
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/low_stock")
    ResponseEntity<List<CreateProductResponse>> getLowStockProduct() {
        var products = productService.getLowStockProducts();
        List<CreateProductResponse> productResponse = products.stream()
                .map(product -> modelMapper.map(product, CreateProductResponse.class))
                .toList();
        return ResponseEntity.ok(productResponse);
    }

}
