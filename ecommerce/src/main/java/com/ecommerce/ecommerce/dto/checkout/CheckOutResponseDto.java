package com.ecommerce.ecommerce.dto.checkout;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutResponseDto {
    private String id;
    private String productId;
    private String productName;
    private String image;
    private int productPrice;
    private int sum;
}
