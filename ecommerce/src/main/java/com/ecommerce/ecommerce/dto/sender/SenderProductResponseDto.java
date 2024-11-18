package com.ecommerce.ecommerce.dto.sender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SenderProductResponseDto {
    private String id;
    private String productName;
    private int productQuantity;
    private int productPrice;
    private String category;
}
