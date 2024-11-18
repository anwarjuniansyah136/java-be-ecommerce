package com.ecommerce.ecommerce.dto.sender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SenderResponseDto {
    private String id;
    private String name;
}
