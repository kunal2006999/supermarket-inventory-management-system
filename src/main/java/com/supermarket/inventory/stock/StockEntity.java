package com.supermarket.inventory.stock;

import com.supermarket.inventory.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "stock")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private int changeQuantity;

    @Column(nullable = false)
    private int finalQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockChangeType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    private String remarks;

    @PrePersist
    public void setTimestamp() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    @Version
    private Integer version;

}

