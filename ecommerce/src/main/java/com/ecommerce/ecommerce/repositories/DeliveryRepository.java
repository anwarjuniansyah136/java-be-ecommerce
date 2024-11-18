package com.ecommerce.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Delivery;
import com.ecommerce.ecommerce.model.Sender;

public interface DeliveryRepository extends JpaRepository<Delivery,String>{
    List<Delivery> findBySender(Sender sender);
}
