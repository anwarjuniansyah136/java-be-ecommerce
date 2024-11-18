package com.ecommerce.ecommerce.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.services.cart.CartService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/post")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> postCart(@RequestParam String productId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(cartService.posCart(productId), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @GetMapping("/get-all")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getAllCart(){
        try{
            return ResponseEntity.ok().body(GenericResponse.success(cartService.getCarts(), "success"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @PutMapping("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> updateCart(@RequestParam String id,@RequestParam String productId){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(null, "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCart(@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(cartService.deleteCart(id), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
}
