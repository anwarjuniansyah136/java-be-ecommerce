package com.ecommerce.ecommerce.services.sender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.ecommerce.dto.sender.SenderResponseDto;
import com.ecommerce.ecommerce.model.Delivery;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.Sender;
import com.ecommerce.ecommerce.repositories.DeliveryRepository;
import com.ecommerce.ecommerce.repositories.SenderRepository;

@Service
public class SenderServiceImpl implements SenderService{
    @Autowired
    private SenderRepository senderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<SenderResponseDto> getSender() {
        return senderRepository.findAll().stream().map(this::toSenderResponseDto).toList();
    }

    private SenderResponseDto toSenderResponseDto(Sender sender){
        return SenderResponseDto.builder()
                .id(sender.getId())
                .name(sender.getName())
                .build();
    }

    @Override
    public List<ProductResponseDto> getBySender() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Sender sender = senderRepository.findByName(email);
        List<Delivery> deliveries = deliveryRepository.findBySender(sender);List<Product> allProducts = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            allProducts.add(delivery.getProduct());
        }
        return allProducts.stream().map(this::toProductResponseDto).toList();
    }

    private ProductResponseDto toProductResponseDto(Product product){
        try {
            return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productQuantity(product.getProductQuantity())
                .category(product.getCategory().getCategoryName())
                .date(dateToString(product.getDate()))
                .image(product.getImage())
                .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    private String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  // Tentukan format yang diinginkan.
        return formatter.format(date);
    }
}
