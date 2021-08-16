package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.entity.Cart;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;

public interface CartService {

    Cart updateCart(CartDto cartDto, String email);
    Cart getCurrentCart(String email);
    void updateCartStatus(CartStatus cartStatus, String email);
    Object applyCouponOnCart(Integer cartId, String email);

}
