package com.ecommerce.ecommerce.services.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.delivery.DeliveryRequestDto;
import com.ecommerce.ecommerce.dto.delivery.DeliveryResponseDto;
import com.ecommerce.ecommerce.model.Auth;
import com.ecommerce.ecommerce.model.Delivery;
import com.ecommerce.ecommerce.model.DeliverySuccess;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.Sender;
import com.ecommerce.ecommerce.repositories.AuthRepository;
import com.ecommerce.ecommerce.repositories.DeliveryRepository;
import com.ecommerce.ecommerce.repositories.DeliverySuccessRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;
import com.ecommerce.ecommerce.repositories.SenderRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private SenderRepository senderRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private DeliverySuccessRepository deliverySuccessRepository;

    @Override
    public String postSender(DeliveryRequestDto dto) {
        Product product = productRepository.getReferenceById(dto.getProductId());
        Sender sender = senderRepository.getReferenceById(dto.getSenderId());
        Delivery delivery = new Delivery();
        delivery.setProduct(product);
        delivery.setSender(sender);
        delivery.setSum(dto.getSum());
        deliveryRepository.save(delivery);
        return "success";
    }

    @Override
    public List<DeliveryResponseDto> getAll() {
        return deliveryRepository.findAll().stream().map(this::toDeliveryResponseDto).toList();
    }
    
    private DeliveryResponseDto toDeliveryResponseDto(Delivery delivery){
        return DeliveryResponseDto.builder()
                .id(delivery.getId())
                .productId(delivery.getProduct().getId())
                .productName(delivery.getProduct().getProductName())
                .productPrice(delivery.getProduct().getProductPrice())
                .image(delivery.getProduct().getImage())
                .kurirName(delivery.getSender().getName())
                .sum(delivery.getSum())
                .build();
    }

    @Override
    public String deliveryDesc(String productId, String desc,int sum) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Auth auth2 = authRepository.findByEmail(email);
        Product product = productRepository.getReferenceById(productId);
        DeliverySuccess del = new DeliverySuccess();
        del.setAuth(auth2);
        del.setProduct(product);
        del.setDesc(desc);
        del.setSum(sum);
        deliverySuccessRepository.save(del);
        return "success";
    }

    @Override
    public String delete(String id) {
        Delivery delivery = deliveryRepository.getReferenceById(id);
        deliveryRepository.delete(delivery);
        return "success";
    }
}
