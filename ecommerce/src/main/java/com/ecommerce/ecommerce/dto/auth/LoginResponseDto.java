package com.ecommerce.ecommerce.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String email;
    private String role;
    private String token;
}
