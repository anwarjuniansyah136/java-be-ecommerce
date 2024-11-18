package com.ecommerce.ecommerce.controller.product;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpHeaders;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.dto.product.ProductRequestDto;
import com.ecommerce.ecommerce.services.product.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/get-all")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getAllProduct(){
        try {
            System.out.println("aaaaaaa");
            return ResponseEntity.ok().body(GenericResponse.success(productService.getAllProducts(), "Success Fetch Data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
    @PostMapping(value = "/post-product")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> postProduct(@RequestBody ProductRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(productService.postProduct(dto), "Success Fetch Data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
    @PutMapping("/put-product")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> putProduct(@RequestParam String id,@RequestBody ProductRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(productService.putProduct(id, dto), "Success Fetch Data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
    @DeleteMapping("/delete-product")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> deleteProduct(@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(productService.deleteProduct(id), "Success Fetch Data"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PutMapping(value = "/upload-photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> uploadPhoto(@RequestPart("photo") MultipartFile photo){
        try {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            return ResponseEntity.ok().body(GenericResponse.success(productService.uploadPhoto(photo), "Success Fetch Data"));
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

    @GetMapping("/find-all-exception-choose")
    public ResponseEntity<Object> findAllExceptionChoose(@RequestParam String productId,@RequestParam String categoryName){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(productService.findAll(productId,categoryName), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @GetMapping("/new-product")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> newProduct(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(productService.getNewProduct(), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PutMapping("/min-stock")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> minStock(@RequestParam String productId,@RequestParam int sum){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(productService.minStock(productId, sum), productId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
}
