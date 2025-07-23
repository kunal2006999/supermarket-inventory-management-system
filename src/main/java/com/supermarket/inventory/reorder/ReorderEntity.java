package com.supermarket.inventory.reorder;

import com.supermarket.inventory.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "reorder")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReorderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @NonNull
    @Column(nullable = false)
    private int requestedQty;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

}

