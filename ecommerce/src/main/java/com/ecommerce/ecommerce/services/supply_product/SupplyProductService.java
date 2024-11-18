package com.ecommerce.ecommerce.services.supply_product;

import java.util.List;

import com.ecommerce.ecommerce.dto.supply_product.SupplyProductDto;
import com.ecommerce.ecommerce.dto.supply_product.SupplyProductResponseDto;

public interface SupplyProductService {
    public String post(SupplyProductDto dto);
    public String update(String id,SupplyProductDto dto);
    public String delete(String id);
    public List<SupplyProductResponseDto> get();
}
