package com.ecommerce.ecommerce.controller.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.services.checkout.CheckOutService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {
    @Autowired
    private CheckOutService checkOutService;

    @PostMapping("/post")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> postCheckOut(@RequestParam String productId,@RequestParam int sum){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(checkOutService.checkOutPost(productId,sum), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @GetMapping("/find-all")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getAll(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(checkOutService.getAll(), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> delete(@RequestParam String id){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(checkOutService.delete(id), "success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

}
