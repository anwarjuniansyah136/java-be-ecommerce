package com.ecommerce.ecommerce.services.sender;

import java.util.List;

import com.ecommerce.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.ecommerce.dto.sender.SenderResponseDto;

public interface SenderService {
    public List<SenderResponseDto> getSender();
    public List<ProductResponseDto> getBySender();
}
