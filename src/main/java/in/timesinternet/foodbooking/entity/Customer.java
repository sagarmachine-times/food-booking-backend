package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.AddressContact;
import in.timesinternet.foodbooking.repository.CartRepository;
import in.timesinternet.foodbooking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {

    String actualEmail;

    @CollectionTable
    @ElementCollection
    private List<AddressContact> addressList = new ArrayList<>();
    //relationship

    @OneToOne(cascade = CascadeType.REMOVE)
    Cart currentCart;

    @ManyToOne
    Restaurant restaurant;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    List<Cart> cartList = new ArrayList<>();

    public void addCart(Cart cart) {
        cartList.add(cart);
        cart.setCustomer(this);
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setCustomer(this);
    }

}
