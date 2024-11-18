package com.ecommerce.ecommerce.services.checkout;

import java.util.List;

import com.ecommerce.ecommerce.dto.checkout.CheckOutResponseDto;

public interface CheckOutService {
    public String checkOutPost(String productId,int sum);
    public List<CheckOutResponseDto> getAll();
    public String delete(String id);
}
