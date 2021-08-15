package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    Integer total=0;

    @Enumerated(EnumType.STRING)
    CartStatus status = CartStatus.MUTABLE;

//    relationships

    @ManyToOne
    @JsonIgnore
    Customer customer;

    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> cartItemList = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
        cartItem.setCart(this);
    }

}
