package com.ecommerce.ecommerce.services.delivery;

import java.util.List;

import com.ecommerce.ecommerce.dto.delivery.DeliveryRequestDto;
import com.ecommerce.ecommerce.dto.delivery.DeliveryResponseDto;

public interface DeliveryService {
    public String postSender(DeliveryRequestDto dto);
    public List<DeliveryResponseDto> getAll();
    public String deliveryDesc(String productId,String desc,int sum);
    public String delete(String id);
}
