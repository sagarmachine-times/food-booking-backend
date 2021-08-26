package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import in.timesinternet.foodbooking.entity.embeddable.Next;
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

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    Date createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
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
    List<Next> next = new ArrayList<>();

    @Transient
    String stage;

    @PostLoad
    public void populateNext() {
        next = new ArrayList<>();
        switch (status) {
            case APPROVED:
                next.add(new Next("Preparing", OrderStatus.PREPARING.toString()));
                break;
            case PENDING:
                next.add(new Next("Approve", OrderStatus.APPROVED.toString()));
                next.add(new Next("Decline", OrderStatus.DECLINED.toString()));
                break;
            case PREPARING:
                next.add(new Next("Pack", OrderStatus.PACKED.toString()));
        }

        switch (status) {
            case PREPARING:stage="PREPARING";break;
            case DECLINED:stage="DECLINED";break;
            case CANCELED:stage="CANCELED";break;
            case PACKED:stage="PACKED";break;
            case PENDING:stage="PENDING";break;
            case APPROVED:stage="APPROVED";break;
            case COMPLETED:stage="COMPLETED";break;
        }

    }


}
