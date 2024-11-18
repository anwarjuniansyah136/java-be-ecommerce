package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.CheckOut;

public interface CheckOutRepository extends JpaRepository<CheckOut,String>{
    
}
