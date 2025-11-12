package com.supermarket.inventory.bill;

import com.supermarket.inventory.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    List<BillEntity> findAllBySaleDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
