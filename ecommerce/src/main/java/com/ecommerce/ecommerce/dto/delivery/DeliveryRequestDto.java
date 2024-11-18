package com.ecommerce.ecommerce.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDto {
    private String productId;
    private String senderId;
    private int sum;
}
