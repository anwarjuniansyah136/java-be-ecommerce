package com.ecommerce.ecommerce.controller.auth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.dto.auth.LoginRequestDto;
import com.ecommerce.ecommerce.dto.auth.RegisterRequestDto;
import com.ecommerce.ecommerce.services.auth.AuthService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(authService.login(dto), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(authService.register(dto), "register successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PostMapping(value = "/upload-photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadPhoto(@RequestParam String id,@RequestParam("photo") MultipartFile file){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(authService.uploadPhoto(file, id), "Success Fetch Data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @GetMapping("/profil")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> profil(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(authService.profil(), "Success Fetch Data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @GetMapping("/photos/{filename}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String filename) {
       try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file path", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not determine file type", e);
        }
    }
}
