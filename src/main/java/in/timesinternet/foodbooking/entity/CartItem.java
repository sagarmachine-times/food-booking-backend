package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    @JsonIgnore
    Integer price;

    Integer quantity;

    //relationship
    @ManyToOne
    @JsonIgnore
    Cart cart;

    @ManyToOne
    Item item;

    @Override
    public int hashCode() {
        System.out.println("HASH CODE---> "+ this.item.id);
        return this.item.id;
    }
}
