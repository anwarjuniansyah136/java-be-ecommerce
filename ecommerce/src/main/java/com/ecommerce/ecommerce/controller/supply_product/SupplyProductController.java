package com.ecommerce.ecommerce.controller.supply_product;

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
import com.ecommerce.ecommerce.dto.supply_product.SupplyProductDto;
import com.ecommerce.ecommerce.services.supply_product.SupplyProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/supply-product")
public class SupplyProductController {

    @Autowired
    private SupplyProductService supplyProductService;
    
    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getAll(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(supplyProductService.get(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PostMapping("/post")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> post(@RequestBody SupplyProductDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(supplyProductService.post(dto), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
    
    @PutMapping("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> put(@RequestParam String id,@RequestBody SupplyProductDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(supplyProductService.update(id, dto), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> delete(@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(supplyProductService.delete(id), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
}