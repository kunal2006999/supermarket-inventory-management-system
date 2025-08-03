package com.supermarket.inventory.supplier;

import com.supermarket.inventory.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "supllier")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String phone;

    @Column(nullable = false)
    @NonNull
    private String email;

    //@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ProductEntity> products = new ArrayList<>();

}


