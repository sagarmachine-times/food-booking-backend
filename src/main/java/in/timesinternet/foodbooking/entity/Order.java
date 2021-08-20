package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import in.timesinternet.foodbooking.entity.enumeration.OrderStatus;
import in.timesinternet.foodbooking.entity.enumeration.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    Integer total;
    Integer deliveryCharge = 0;
    Integer discount = 0;

    @Enumerated(value = EnumType.STRING)
    OrderStatus status;

    @Enumerated(value = EnumType.STRING)
    OrderType type;

    Boolean isCouponApplied = false;

    @Embedded
    Contact contact;

    @Embedded
    Address address;


    //relationship
    @ManyToOne
            @JsonIgnore
    Restaurant restaurant;

    @ManyToOne
    @JsonIgnore
    Customer customer;

    @ManyToOne
    Coupon coupon;

    @OneToOne
    Cart cart;

    @OneToOne
    Package pack;

    @OneToOne
    Payment payment;

}
