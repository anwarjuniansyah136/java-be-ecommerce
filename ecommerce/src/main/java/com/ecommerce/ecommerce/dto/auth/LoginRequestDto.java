package com.ecommerce.ecommerce.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}
