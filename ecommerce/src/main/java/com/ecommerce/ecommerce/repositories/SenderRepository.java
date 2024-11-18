package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Sender;

public interface SenderRepository extends JpaRepository<Sender,String>{

    Sender findByName(String email);
    
}
