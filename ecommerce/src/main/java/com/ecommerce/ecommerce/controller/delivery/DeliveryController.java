package com.ecommerce.ecommerce.controller.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.dto.delivery.DeliveryRequestDto;
import com.ecommerce.ecommerce.services.delivery.DeliveryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;
    
    @PostMapping("/post")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> post(@RequestBody DeliveryRequestDto dto){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(deliveryService.postSender(dto), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
    
    @GetMapping("/find-all")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> findAll(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(deliveryService.getAll(), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PostMapping("/success")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> post(@RequestParam String productId,@RequestParam String desc,@RequestParam int sum){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(deliveryService.deliveryDesc(productId, desc,sum), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> delete(@RequestParam String id){
        try {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: " + id);
            return ResponseEntity.ok().body(GenericResponse.success(deliveryService.delete(id), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
}
