package in.timesinternet.foodbooking.contoller.customer;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.entity.Cart;
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
}
