package com.ecommerce.ecommerce.dto.product;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequestDto {
    private String productName;
    private int productQuantity;
    private int productPrice;
    private String category;
}
