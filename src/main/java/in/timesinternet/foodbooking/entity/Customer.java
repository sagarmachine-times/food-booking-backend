package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User{

    String actualEmail;


    //relationship

    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Cart> cartList = new ArrayList<>();
    public void addCart(Cart cart) {
        cartList.add(cart);
        cart.setCustomer(this);
    }

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Order> orderList = new ArrayList<>();
    public void addOrder(Order order) {
        orderList.add(order);
        order.setCustomer(this);
    }

}
