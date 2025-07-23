package com.supermarket.inventory.product;

import com.supermarket.inventory.supplier.SupplierEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "product")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String category;

    private String brand;

    @Column(nullable = false)
    @NonNull
    private BigDecimal pricePerUnit;

    private int quantityInStock;

    private int reorderLevel;

    private LocalDate expiryDate;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;


}
