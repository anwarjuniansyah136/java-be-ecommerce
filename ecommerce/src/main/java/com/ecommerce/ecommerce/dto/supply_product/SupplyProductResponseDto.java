package com.ecommerce.ecommerce.dto.supply_product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyProductResponseDto {
    private String id;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private String category;
    private String companyName;
    private String date;    
}
