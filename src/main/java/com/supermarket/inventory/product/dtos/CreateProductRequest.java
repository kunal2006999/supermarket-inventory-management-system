package com.supermarket.inventory.product.dtos;

import com.supermarket.inventory.supplier.SupplierEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Setter(AccessLevel.NONE)
public class CreateProductRequest {

    @NonNull
    private String name;

    @NonNull
    private String category;

    @NonNull
    private String brand;

    @NonNull
    private BigDecimal pricePerUnit;

    private String unit;

    @NonNull
    private int quantityInStock;

    @NonNull
    private int reorderLevel;

    private boolean isDeleted = false;

    @NonNull
    private Long SupplierId;
}
