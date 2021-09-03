package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.enumeration.CartStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    Date createdAt;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @UpdateTimestamp
    Date updatedAt;

    Integer total=0;

    Integer discount=0;

    @Enumerated(EnumType.STRING)
    CartStatus status = CartStatus.MUTABLE;

//    relationships

    @ManyToOne
    Coupon coupon;

    @ManyToOne
    @JsonIgnore
    Customer customer;

    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<CartItem> cartItemList = new HashSet<>();

    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
        cartItem.setCart(this);
    }

}
