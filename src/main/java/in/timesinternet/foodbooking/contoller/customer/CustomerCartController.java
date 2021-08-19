package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.dto.request.CartItemUpdateDto;
import in.timesinternet.foodbooking.entity.Cart;
import in.timesinternet.foodbooking.entity.CartItem;
import in.timesinternet.foodbooking.service.CartService;
import in.timesinternet.foodbooking.service.impl.BindingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer/cart")
public class CustomerCartController {

    @Autowired
    CartService cartService;

    @Autowired
    BindingResultService bindingResultService;

    @PutMapping("")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<Cart> updateCart(@RequestBody @Valid CartDto cartDto, HttpServletRequest request, BindingResult bindingResult){
        String userEmail = (String) request.getAttribute("userEmail");
        bindingResultService.validate(bindingResult);
        return ResponseEntity.ok(cartService.updateCart(cartDto, userEmail));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<Cart> getCart(HttpServletRequest request){
        String userEmail = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(cartService.getCurrentCart(userEmail));
    }

    @PutMapping("/cart_item")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody @Valid CartItemUpdateDto cartItemUpdateDto,
                                                    HttpServletRequest request, BindingResult bindingResult){
        bindingResultService.validate(bindingResult);
        String userEmail = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(cartService.updateCartItemQuantity(cartItemUpdateDto, userEmail));
    }

    @PutMapping("/cart_item/{itemId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<CartItem> addItemToCart(@PathVariable Integer itemId, HttpServletRequest request){
        String userEmail = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(cartService.addItemToCart(itemId, userEmail));
    }

    @DeleteMapping("/cart_item/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<CartItem> deleteCartItem(@PathVariable Integer cartItemId, HttpServletRequest request){
        String userEmail = (String) request.getAttribute("userEmail");
        return ResponseEntity.ok(cartService.deleteCartItem(cartItemId, userEmail));
    }
}
