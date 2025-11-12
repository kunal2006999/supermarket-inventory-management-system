package com.supermarket.inventory.product;

import com.supermarket.inventory.product.dtos.CreateProductRequest;
import com.supermarket.inventory.product.dtos.UpdateProductRequest;
import com.supermarket.inventory.users.UserEntity;
import com.supermarket.inventory.users.UserService;
import com.supermarket.inventory.users.UsersRepository;
import com.supermarket.inventory.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductEntity createProduct(CreateProductRequest p) {
        ProductEntity newProduct = modelMapper.map(p, ProductEntity.class);

        return productRepository.save(newProduct);
    }

    public ProductEntity updateProduct(Long productId, UpdateProductRequest p) {
        ProductEntity product = getProduct(productId);
        if (p.getName() != null) {
            product.setName(p.getName());
        }
        if (p.getCategory() != null) {
            product.setCategory(p.getCategory());
        }
        if (p.getBrand() != null) {
            product.setBrand(p.getBrand());
        }
        if (p.getPricePerUnit() != null) {
            product.setPricePerUnit(p.getPricePerUnit());
        }
        product.setReorderLevel(p.getReorderLevel());
        product.setDeleted(p.isDeleted());

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        ProductEntity product = getProduct(productId);
        productRepository.delete(product);
    }

    public ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductService.ProductNotFoundException(productId));
    }

    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();
    }

    public List<ProductEntity> searchProducts(String name, String category, String brand) {
        return productRepository.findByFilters(name, category, brand, false); // false = not deleted
    }

    public List<ProductEntity> getLowStockProducts() {
        List<ProductEntity> allProducts = productRepository.findAll();
        int defaultReorderLevel = 10;
        return allProducts.stream()
                .filter(p -> p.getQuantityInStock() <
                        ((Integer) p.getReorderLevel() != null ? p.getReorderLevel() : defaultReorderLevel))
                .toList();
    }

    public static class ProductNotFoundException extends IllegalArgumentException {
        public ProductNotFoundException(String name) {
            super("Product with name" + name + " not found");
        }
        public ProductNotFoundException(Long productId) {
            super("Product with id" + productId + " not found");
        }
    }


}
