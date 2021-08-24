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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    Restaurant restaurant;

    @ManyToOne
    @JsonIgnore
    Customer customer;

    @ManyToOne
    Coupon coupon;

    @OneToOne
    Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    Package pack;

    @OneToOne(cascade = CascadeType.ALL)
    Payment payment;

    @Transient
    List<String> next = new ArrayList<>();

    @PostLoad
    void updateNext() {
        switch (status) {
            case APPROVED:next.add(OrderStatus.PACKED.toString());
            next.add(OrderStatus.PREPARING.toString());
            break;
            case PENDING:next.add(OrderStatus.DECLINED.toString());
            next.add(OrderStatus.APPROVED.toString());
            break;
            case PREPARING:next.add(OrderStatus.PACKED.toString());
        }
    }

}
