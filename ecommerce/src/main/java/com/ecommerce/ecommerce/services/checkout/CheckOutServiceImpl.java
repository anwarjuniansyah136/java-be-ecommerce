package com.ecommerce.ecommerce.services.checkout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.checkout.CheckOutResponseDto;
import com.ecommerce.ecommerce.model.Auth;
import com.ecommerce.ecommerce.model.CheckOut;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repositories.AuthRepository;
import com.ecommerce.ecommerce.repositories.CheckOutRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;

@Service
public class CheckOutServiceImpl implements CheckOutService{

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private CheckOutRepository checkOutRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String checkOutPost(String productId,int sum) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Auth auth2 = authRepository.findByEmail(email);
        CheckOut checkOut = new CheckOut();
        Product product = productRepository.getReferenceById(productId);
        checkOut.setAuth(auth2);
        checkOut.setProduct(product);
        checkOut.setSum(sum);
        checkOutRepository.save(checkOut);
        return "success"; 
    }

    @Override
    public List<CheckOutResponseDto> getAll() {
        return checkOutRepository.findAll().stream().map(this::toCheckOutResponseDto).toList();
    }
    
    private CheckOutResponseDto toCheckOutResponseDto(CheckOut checkOut){
        return CheckOutResponseDto.builder()   
                .id(checkOut.getId())
                .productId(checkOut.getProduct().getId())
                .productName(checkOut.getProduct().getProductName())
                .productPrice(checkOut.getProduct().getProductPrice())
                .image(checkOut.getProduct().getImage())
                .sum(checkOut.getSum())
                .build();
    }

    @Override
    public String delete(String id) {
        CheckOut chck = checkOutRepository.getReferenceById(id);
        checkOutRepository.delete(chck);
        return "success";
    }
}
