package com.ecommerce.ecommerce.services.cart;
import java.util.List;

import com.ecommerce.ecommerce.dto.cart.CartResponseDto;

public interface CartService {
    public List<CartResponseDto> getCarts();
    public String posCart(String productId);
    public String upadteCart(String cartId,String productId);
    public String deleteCart(String cartId);
}
