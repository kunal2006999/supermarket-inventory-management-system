package com.supermarket.inventory.stock;

import com.supermarket.inventory.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByProduct(ProductEntity product);
    Optional<StockEntity> findTopByProductOrderByTimestampDesc(ProductEntity product);
}
