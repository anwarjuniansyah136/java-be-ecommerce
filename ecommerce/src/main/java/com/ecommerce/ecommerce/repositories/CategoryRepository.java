package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category,String> {

    Category findByCategoryName(String category);
    
}
