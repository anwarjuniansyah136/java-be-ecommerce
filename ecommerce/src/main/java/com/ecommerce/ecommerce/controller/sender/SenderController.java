package com.ecommerce.ecommerce.controller.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.GenericResponse;
import com.ecommerce.ecommerce.services.sender.SenderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/sender")
public class SenderController {
    @Autowired
    private SenderService senderService;

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getAllSender(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(senderService.getSender(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }

    @GetMapping("/get-by-sender")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getBySender(){
        try {
            return ResponseEntity.ok().body(GenericResponse.success(senderService.getBySender(), "Success"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.error("internal server error"));
        }
    }
}
