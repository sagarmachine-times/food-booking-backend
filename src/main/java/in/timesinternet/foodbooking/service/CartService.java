package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;

public interface CartService {

    Object updateCart(CartDto cartDto, String email);
    void updateCartStatus(CartStatus cartStatus, String email);
    Object applyCouponOnCart(Integer cartId, String email);

}
