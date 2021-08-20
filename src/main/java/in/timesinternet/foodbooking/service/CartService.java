package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.dto.request.CartItemUpdateDto;
import in.timesinternet.foodbooking.entity.Cart;
import in.timesinternet.foodbooking.entity.CartItem;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;

public interface CartService {

    Cart updateCart(CartDto cartDto, String email);

    Cart getCurrentCart(String email);

    Cart updateCartStatus(CartStatus cartStatus, String email);

    Object applyCouponOnCart(Integer cartId, String email);

    CartItem updateCartItemQuantity(CartItemUpdateDto cartItemUpdateDto, String userEmail);

    CartItem addItemToCart(Integer itemId, String userEmail);

    CartItem deleteCartItem(Integer cartItemId, String userEmail);

    Cart addNewCart(String userEmail);
}
