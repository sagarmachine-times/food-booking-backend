package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(nullable = false)
    Integer value;

    @JsonFormat( pattern = "dd-MM-yyyy")
    Date startingDate;

    @NotNull
    @JsonFormat( pattern = "dd-MM-yyyy")
    Date endingDate;


    @Column(nullable = false)
    Integer minimumCartValue;


    @Column(nullable = false)
    Integer maxDiscount;


    @Column(nullable = false)
    Integer maxPerUser;


    @Column(nullable = false)
    Integer totalUse;


    @Column(nullable = false)
    String termsAndCondition;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    Date createdAt;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @UpdateTimestamp
    Date updatedAt;

    //relationship

    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Image banner;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setCoupon(this);
    }

}
