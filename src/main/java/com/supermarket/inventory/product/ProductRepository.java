package com.supermarket.inventory.product;

import com.supermarket.inventory.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM product p WHERE "
            + "(:name IS NULL OR p.name LIKE %:name%) AND "
            + "(:category IS NULL OR p.category = :category) AND "
            + "(:brand IS NULL OR p.brand = :brand) AND "
            + "p.isDeleted = false")
    List<ProductEntity> findByFilters(
            @Param("name") String name,
            @Param("category") String category,
            @Param("brand") String brand,
            @Param("isDeleted") boolean isDeleted
    );

    @Query("SELECT p FROM product p WHERE p.quantityInStock < :reorderLevel")
    List<ProductEntity> findLowStockProducts(@Param("reorderLevel") int reorderLevel);
}
