package com.supermarket.inventory.product;

import com.supermarket.inventory.product.dtos.CreateProductRequest;
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

    public ProductEntity getProduct(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new ProductService.ProductNotFoundException(name));
    }

    public ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductService.ProductNotFoundException(productId));
    }

    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();
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
