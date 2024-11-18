package com.ecommerce.ecommerce.services.auth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.dto.auth.LoginRequestDto;
import com.ecommerce.ecommerce.dto.auth.LoginResponseDto;
import com.ecommerce.ecommerce.dto.auth.ProfilResponseDto;
import com.ecommerce.ecommerce.dto.auth.RegisterRequestDto;
import com.ecommerce.ecommerce.model.Auth;
import com.ecommerce.ecommerce.model.Roles;
import com.ecommerce.ecommerce.repositories.AuthRepository;
import com.ecommerce.ecommerce.repositories.RolesRepository;
import com.ecommerce.ecommerce.utils.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Auth register(RegisterRequestDto dto) {
        Auth auth = new Auth();
        auth.setName(dto.getName());
        auth.setEmail(dto.getEmail());
        auth.setPassword(passwordEncoder.encode(dto.getPassword()));
        Roles roles = rolesRepository.findByRolesName("users");
        auth.setRoles(roles);
        authRepository.save(auth);
        return auth;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        Auth auth = authRepository.findByEmail(dto.getEmail());
        System.out.println(auth);
        System.out.println(dto);
        if(auth != null){
            boolean isMatch = passwordEncoder.matches(dto.getPassword(), auth.getPassword());
            if(isMatch){
                LoginResponseDto responseDto = new LoginResponseDto();
                responseDto.setEmail(auth.getEmail());
                responseDto.setRole(auth.getRoles().getRolesName());
                responseDto.setToken(jwtUtil.generateToken(auth));
                return responseDto;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid username or password");
    }

    @Override
    public ProfilResponseDto profil() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Auth auth2 = authRepository.findByEmail(email);
        ProfilResponseDto response = new ProfilResponseDto();
        response.setEmail(email);
        response.setName(auth2.getName());
        response.setImage(auth2.getImage());
        return response;
    }

    @Override
    public String uploadPhoto(MultipartFile file,String id)throws IOException, SQLException{
        String[] filenameParts = Objects.requireNonNull(file.getResource().getFilename()).split("\\.");
        String extension = filenameParts[filenameParts.length - 1].toLowerCase();

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported file type");
        }

        Path uploadDirectory = Paths.get(uploadDir);
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
        String newFileName = id + "_" + System.currentTimeMillis() + "." + extension;
        Path filePath = uploadDirectory.resolve(newFileName);
        Files.copy(file.getInputStream(), filePath);
        Auth auth = authRepository.getReferenceById(id);
        if (auth != null) {
            auth.setImage(newFileName);
            authRepository.save(auth);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not Found");
        }
        return "success";
    }
    
    
}
