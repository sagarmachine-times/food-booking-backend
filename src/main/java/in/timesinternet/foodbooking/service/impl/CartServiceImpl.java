package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.ApplyCouponResponseDto;
import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.dto.request.CartItemDto;
import in.timesinternet.foodbooking.dto.request.CartItemUpdateDto;
import in.timesinternet.foodbooking.entity.*;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;
import in.timesinternet.foodbooking.exception.InvalidRequestException;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.exception.UnauthorizedException;
import in.timesinternet.foodbooking.repository.*;
import in.timesinternet.foodbooking.service.CartService;
import in.timesinternet.foodbooking.service.CouponService;
import in.timesinternet.foodbooking.service.CustomerService;
import in.timesinternet.foodbooking.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    CouponService couponService;

    @Override
    public Cart updateCart(CartDto cartDto, String email) {
        Customer customer = customerRepository.findByEmail(email).get();
        Cart currentCart = customer.getCurrentCart();
        Integer total = 0;
        System.out.println(currentCart.getCartItemList().size());
        while (!currentCart.getCartItemList().isEmpty())
            currentCart.getCartItemList().remove(0);
        for (CartItemDto cartItemDto : cartDto.getCartItemList()) {
            Optional<Item> itemOptional = itemRepository.findById(cartItemDto.getItemId());
            if (itemOptional.isPresent() && itemOptional.get().getIsAvailable()) {
                Item item = itemOptional.get();
                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setPrice(item.getSellingPrice());
                cartItem.setQuantity(cartItemDto.getQuantity());
                currentCart.addCartItem(cartItem);
                total += cartItem.getQuantity() * cartItem.getPrice();
            }
        }
        currentCart.setTotal(total);
        return cartRepository.save(currentCart);
    }

    @Override
    public Cart getCurrentCart(String email) {
        Customer customer = customerRepository.findByEmail(email).get();
        return customer.getCurrentCart();
    }

    @Override
    public Cart updateCartStatus(CartStatus cartStatus, String email) {
        Customer customer = customerService.getCustomer(email);
        Cart currentCart = customer.getCurrentCart();
        if (cartStatus.equals(CartStatus.IMMUTABLE))
            for (CartItem cartItem : currentCart.getCartItemList())
                cartItem.setPrice(cartItem.getItem().getSellingPrice());
        currentCart.setStatus(cartStatus);
        return cartRepository.save(currentCart);
    }

    @Override
    public Object applyCouponOnCart(Integer cartId, String email) {
        return null;
    }


    @Override
    public Cart updateCartItemQuantity(CartItemUpdateDto cartItemUpdateDto, String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        CartItem cartItem = getCartItem(cartItemUpdateDto.getCartItemId());
        Cart currentCart = customer.getCurrentCart();
        currentCart.setTotal(currentCart.getTotal() - (cartItem.getQuantity() * cartItem.getItem().getSellingPrice()));
        if (customer.getId().equals(cartItem.getCart().getCustomer().getId())) {
            if (customer.getCurrentCart().getStatus().equals(CartStatus.IMMUTABLE))
                throw new UnauthorizedException("cart is immutable");
            if (cartItemUpdateDto.getQuantity() < 0)
                throw new UnauthorizedException("invalid request quantity can't be negative");
            if (cartItemUpdateDto.getQuantity() == 0)
                return deleteCartItem(cartItemUpdateDto.getCartItemId(), userEmail);
            else {
                cartItem.setQuantity(cartItemUpdateDto.getQuantity());
                currentCart.setTotal(currentCart.getTotal() + (cartItem.getQuantity() * cartItem.getItem().getSellingPrice()));
                return cartRepository.save(currentCart);
            }
        } else
            throw new UnauthorizedException("unauthorized request cart doesn't belongs to you");
    }

    @Override
    public Cart addItemToCart(Integer itemId, String userEmail) {
        Item item = itemService.getItem(itemId);
        Customer customer = customerService.getCustomer(userEmail);
        Cart currentCart = customer.getCurrentCart();
        if (customer.getCurrentCart().getStatus().equals(CartStatus.IMMUTABLE))
            throw new UnauthorizedException("cart is immutable");
        if (!customer.getCurrentCart().getRestaurant().getId().equals(customer.getRestaurant().getId()))
            throw new UnauthorizedException("item does not belong to restaurant " + customer.getRestaurant().getId());
        if (customer.getCurrentCart().getCartItemList().parallelStream().anyMatch(existingCartItem -> existingCartItem.getItem().getId().equals(itemId)))
            throw new UnauthorizedException("item already exist");
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(1);
        cartItem.setCart(customer.getCurrentCart());
        currentCart.addCartItem(cartItem);
        currentCart.setTotal(currentCart.getTotal() + item.getSellingPrice());
        return cartRepository.save(currentCart);
    }

    @Override
    public Cart deleteCartItem(Integer cartItemId, String userEmail) {
        CartItem cartItem = getCartItem(cartItemId);
        Customer customer = customerService.getCustomer(userEmail);
        Cart currentCart = customer.getCurrentCart();
        if (customer.getCurrentCart().getStatus().equals(CartStatus.IMMUTABLE))
            throw new UnauthorizedException("cart is immutable");
        if (cartItem.getCart().getCustomer().getId().equals(customer.getId()) && cartItem.getCart().getId().equals(customer.getCurrentCart().getId())) {
            currentCart.setTotal(currentCart.getTotal() - (cartItem.getItem().getSellingPrice() * cartItem.getQuantity()));
            cartItemRepository.deleteById(cartItemId);
            return cartRepository.save(currentCart);
        } else
            throw new UnauthorizedException("unauthorized request cart dont belong to you");
    }

    @Override
    public Cart addNewCart(String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        if (customer.getCurrentCart().getStatus().equals(CartStatus.MUTABLE))
            throw new InvalidRequestException("invalid request cart is immutable");
        Cart cart = new Cart();
        cart.setRestaurant(customer.getRestaurant());
        customer = customerRepository.save(customer);
        customer.setCurrentCart(cart);
        customer.addCart(customer.getCurrentCart());
        cartRepository.save(cart);
        customerRepository.save(customer);
        return cart;
    }

    private CartItem getCartItem(Integer id) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isPresent())
            return cartItemOptional.get();
        throw new UnauthorizedException(("cart item not found with id " + id));
    }


    @Override
    public ApplyCouponResponseDto addCouponOnCurrentCart(String email, String couponName) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Cart currentCart = customer.getCurrentCart();

            couponName = couponName.toUpperCase().trim();
            Optional<Coupon> couponOptional = couponRepository.findByName(couponName);

            if (couponOptional.isPresent()) {
                Coupon coupon = couponOptional.get();

                ApplyCouponResponseDto applyCouponResponseDto = new ApplyCouponResponseDto();
                applyCouponResponseDto.setOldTotal(currentCart.getTotal());

                String message = "";
                int cartValue = currentCart.getTotal();
                int minCartValue = coupon.getMinimumCartValue();


                if (cartValue < minCartValue)
                    throw new InvalidRequestException("minimum cart value required is "+cartValue);

                int discountPercentage = coupon.getValue();
                int maxDiscountValue = coupon.getMaxDiscount();
                int discountedValue = (discountPercentage * cartValue) / 100;
                discountedValue = Math.min(discountedValue, maxDiscountValue);


                applyCouponResponseDto.setNewTotal(cartValue-discountedValue);
                applyCouponResponseDto.setDiscount(discountedValue);
                applyCouponResponseDto.setMessage("you have saved "+discountedValue);
                applyCouponResponseDto.setCouponId(coupon.getId());
                return applyCouponResponseDto;
            } else {
                throw new NotFoundException("this coupon is not found");
            }
        } else {
            throw new NotFoundException("this customer is not found");
        }
    }
}
