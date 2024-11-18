package com.ecommerce.ecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private String id;
    private String productName;
    private int productQuantity;
    private int productPrice;
    private String category;
    private String image;
    private String date;
}
