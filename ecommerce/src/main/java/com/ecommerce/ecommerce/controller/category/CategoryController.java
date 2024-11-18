package com.ecommerce.ecommerce.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.dto.category.CategoryRequestDto;
import com.ecommerce.ecommerce.services.category.CategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/category")
@Tag(name = "Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getCategory(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(categoryService.getAll(), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PostMapping("/post")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> postCategory(@RequestBody CategoryRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(categoryService.postCategory(dto), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PutMapping("/put")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> putCategory(@RequestBody CategoryRequestDto dto,@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(categoryService.putCategory(dto,id), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> deleteCategory(@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(categoryService.deleteCategory(id), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
}
