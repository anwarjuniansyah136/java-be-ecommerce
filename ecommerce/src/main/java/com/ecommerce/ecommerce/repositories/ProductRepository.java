package com.ecommerce.ecommerce.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product,String>{
    List<Product> findByCategory(Category category);
    @Query("SELECT p FROM Product p WHERE p.date >= :lastMonth")
    List<Product> findProductsAddedInLastMonth(@Param("lastMonth") LocalDateTime lastMonth);
}
