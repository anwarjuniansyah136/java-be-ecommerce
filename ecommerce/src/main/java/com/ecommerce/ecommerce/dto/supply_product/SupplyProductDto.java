package com.ecommerce.ecommerce.dto.supply_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyProductDto {
    private String productName;
    private int productPrice;
    private int productQuantity;
    private String date;
    private String companyName;
    private String category;
}
