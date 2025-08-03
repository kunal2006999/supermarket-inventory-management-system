package com.supermarket.inventory.bill;

import com.supermarket.inventory.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Entity(name = "saleItem")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bill_id")
    private BillEntity bill;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(nullable = false)
    @NonNull
    private int quantity;

    @Column(nullable = false)
    @NonNull
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal total;

    private double pricePerUnitAtSale;


}
