package com.supermarket.inventory.bill;

import com.supermarket.inventory.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "bill")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    @NonNull
    private String customerName;

    private LocalDateTime saleDateTime;

    @Column(nullable = false)
    @NonNull
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String paymentMode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    private UserEntity staff; // Assuming staff is a user

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemEntity> saleItems = new ArrayList<>();


}
