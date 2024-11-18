package com.ecommerce.ecommerce.services.cart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.dto.cart.CartResponseDto;
import com.ecommerce.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.ecommerce.model.Auth;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repositories.AuthRepository;
import com.ecommerce.ecommerce.repositories.CartRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuthRepository authRepository;

    @Override
    public List<CartResponseDto> getCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(this::toCartDto).collect(Collectors.toList());
    }

    @Override
    public String posCart(String productId) {
        Product product = productRepository.getReferenceById(productId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Auth auth = authRepository.findByEmail(email);
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setAuth(auth);
        cartRepository.save(cart);
        return "success";
    }

    @Override
    public String upadteCart(String cartId,String productId) {
        Product product = productRepository.getReferenceById(productId);
        Cart cart = cartRepository.getReferenceById(productId);
        cart.setProduct(product);
        cartRepository.save(cart);
        return "success";
    }

    @Override
    public String deleteCart(String cartId) {
        Cart cart = cartRepository.getReferenceById(cartId);
        cartRepository.delete(cart);
        return "success";
    }

    private CartResponseDto toCartDto(Cart cart){
        return CartResponseDto.builder()
                .id(cart.getId())
                .product(toProductResponseDto(cart.getProduct()))
                .build();
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    private String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  // Tentukan format yang diinginkan.
        return formatter.format(date);
    }
    
}
