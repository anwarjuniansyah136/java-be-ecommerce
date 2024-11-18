package com.ecommerce.ecommerce.dto.cart;

import com.ecommerce.ecommerce.dto.product.ProductResponseDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDto {
    private String id;
    private ProductResponseDto product;
}
