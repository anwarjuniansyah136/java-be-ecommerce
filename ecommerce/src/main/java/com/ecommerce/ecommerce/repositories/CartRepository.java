package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Cart;

public interface CartRepository extends JpaRepository<Cart,String>{
    
}
