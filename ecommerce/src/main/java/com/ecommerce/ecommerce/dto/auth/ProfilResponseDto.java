package com.ecommerce.ecommerce.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilResponseDto {
    private String name;
    private String email;
    private String image;
}
