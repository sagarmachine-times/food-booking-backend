package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.CartDto;
import in.timesinternet.foodbooking.dto.request.CartItemDto;
import in.timesinternet.foodbooking.entity.*;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;
import in.timesinternet.foodbooking.repository.CartRepository;
import in.timesinternet.foodbooking.repository.CustomerRepository;
import in.timesinternet.foodbooking.repository.ItemRepository;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.service.CartService;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Cart updateCart(CartDto cartDto, String email) {
        Customer customer = customerRepository.findByEmail(email).get();
        Cart currentCart = customer.getCurrentCart();
//        List<CartItem> cartItemList = new ArrayList<>();
        Integer total= 0;
        System.out.println(currentCart.getCartItemList().size());
        System.out.println("------------------");
        while(!currentCart.getCartItemList().isEmpty())
            currentCart.getCartItemList().remove(0);
        System.out.println(currentCart.getCartItemList().size());
        System.out.println("------------------");
        for(CartItemDto cartItemDto: cartDto.getCartItemList()){
            Optional<Item> itemOptional = itemRepository.findById(cartItemDto.getItemId());
            if(itemOptional.isPresent() && itemOptional.get().getIsAvailable()){
                Item item= itemOptional.get();
                CartItem cartItem =  new CartItem();
                cartItem.setItem(item);
                cartItem.setPrice(item.getSellingPrice());
                cartItem.setQuantity(cartItemDto.getQuantity());
                currentCart.addCartItem(cartItem);
                total+=cartItem.getQuantity()*cartItem.getPrice();
            }
        }
        System.out.println(currentCart.getCartItemList().size());
        System.out.println("------------------");
        currentCart.setTotal(total);
//        currentCart.setCartItemList(cartItemList);
        return  cartRepository.save(currentCart);
    }

    @Override
    public void updateCartStatus(CartStatus cartStatus, String email) {

    }

    @Override
    public Object applyCouponOnCart(Integer cartId, String email) {
        return null;
    }
}
