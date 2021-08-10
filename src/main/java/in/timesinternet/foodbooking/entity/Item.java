package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.enumeration.ItemType;
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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    Boolean isAvailable;
    Integer actualPrice;
    Integer sellingPrice;

    @Enumerated(EnumType.STRING)
    ItemType itemType;


    //relationships
    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;

    @ManyToOne
    @JsonIgnore
    Category category;

    @OneToOne(cascade = CascadeType.ALL)
    Image image;


    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<CartItem> cartItemList = new ArrayList<>();
    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
        cartItem.setItem(this);
    }
}
