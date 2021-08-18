package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.dto.request.CartItemDto;
import in.timesinternet.foodbooking.dto.request.CartItemUpdateDto;
import in.timesinternet.foodbooking.entity.*;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;
import in.timesinternet.foodbooking.repository.*;
import in.timesinternet.foodbooking.service.CartService;
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
    public void updateCartStatus(CartStatus cartStatus, String email) {

    }

    @Override
    public Object applyCouponOnCart(Integer cartId, String email) {
        return null;
    }


    @Override
    public CartItem updateCartItemQuantity(CartItemUpdateDto cartItemUpdateDto, String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        CartItem cartItem = getCartItem(cartItemUpdateDto.getCartItemId());

        if (customer.getId().equals(cartItem.getCart().getCustomer().getId())) {
            if (customer.getCurrentCart().getStatus().equals(CartStatus.IMMUTABLE))
                throw new RuntimeException("cart is immutable");
            if (cartItemUpdateDto.getQuantity() < 0)
                throw new RuntimeException("invalid request quantity can't be negative");
            if (cartItemUpdateDto.getQuantity() == 0)
                deleteCartItem(cartItemUpdateDto.getCartItemId(), userEmail);
            else
                cartItem.setQuantity(cartItemUpdateDto.getQuantity());
            return cartItemRepository.save(cartItem);
        } else
            throw new RuntimeException("unauthorized request cart doesn't belongs to you");
    }

    @Override
    public CartItem addItemToCart(Integer itemId, String userEmail) {
        Item item = itemService.getItem(itemId);
        Customer customer = customerService.getCustomer(userEmail);
        if (customer.getCurrentCart().getStatus().equals(CartStatus.IMMUTABLE))
            throw new RuntimeException("cart is immutable");
        if (!customer.getCurrentCart().getRestaurant().getId().equals(customer.getRestaurant().getId()))
            throw new RuntimeException("item does not belong to restaurant "+customer.getRestaurant().getId());
        if (customer.getCurrentCart().getCartItemList().parallelStream().anyMatch(existingCartItem ->existingCartItem.getItem().getId().equals(itemId)))
            throw new RuntimeException("item already exist");
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);

        cartItem.setQuantity(1);
        cartItem.setCart(customer.getCurrentCart());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem deleteCartItem(Integer cartItemId, String userEmail) {
        CartItem cartItem = getCartItem(cartItemId);
        Customer customer = customerService.getCustomer(userEmail);
        if (customer.getCurrentCart().getStatus().equals(CartStatus.IMMUTABLE))
            throw new RuntimeException("cart is immutable");
        if (cartItem.getCart().getCustomer().getId().equals(customer.getId()) && cartItem.getCart().getId().equals(customer.getCurrentCart().getId())) {
            cartItemRepository.deleteById(cartItemId);
            return cartItem;
        }
        else
            throw new RuntimeException("unauthorized request cart dont belong to you");
    }

    private CartItem getCartItem(Integer id) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isPresent())
            return cartItemOptional.get();
        throw new RuntimeException(("cart item not found with id " + id));
    }
}
