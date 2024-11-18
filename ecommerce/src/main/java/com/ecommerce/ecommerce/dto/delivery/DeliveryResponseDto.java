package com.ecommerce.ecommerce.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseDto {
    private String id;
    private String productId;
    private String productName;
    private int productPrice;
    private String image; 
    private String kurirName;
    private int sum;
}
