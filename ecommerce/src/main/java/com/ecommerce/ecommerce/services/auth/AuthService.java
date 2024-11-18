package com.ecommerce.ecommerce.services.auth;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.dto.auth.LoginRequestDto;
import com.ecommerce.ecommerce.dto.auth.LoginResponseDto;
import com.ecommerce.ecommerce.dto.auth.ProfilResponseDto;
import com.ecommerce.ecommerce.dto.auth.RegisterRequestDto;
import com.ecommerce.ecommerce.model.Auth;

public interface AuthService {
    public Auth register(RegisterRequestDto dto);
    public LoginResponseDto login(LoginRequestDto dto);
    public ProfilResponseDto profil();
    public String uploadPhoto(MultipartFile file,String id)throws IOException, SQLException;
}
