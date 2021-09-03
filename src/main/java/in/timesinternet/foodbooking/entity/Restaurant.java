package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.RestaurantDetail;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @Embedded
    RestaurantDetail restaurantDetail;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    Date createdAt;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @UpdateTimestamp
    Date updatedAt;

    //relationship
    @OneToOne(cascade = CascadeType.ALL)
    Image logo;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Staff> staffList = new ArrayList<>();
    public void addStaff(Staff staff) {
        staffList.add(staff);
        staff.setRestaurant(this);
    }

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Customer> customerList = new ArrayList<>();
    public void addCustomer(Customer customer) {
        customerList.add(customer);
        customer.setRestaurant(this);
    }

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Category> categoryList = new ArrayList<>();
    public void addCategory(Category category) {
        categoryList.add(category);
        category.setRestaurant(this);
    }

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Item> itemList = new ArrayList<>();
    public void addItem(Item item) {
        itemList.add(item);
        item.setRestaurant(this);
    }

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Cart> cartList = new ArrayList<>();
    public void addCart(Cart cart) {
        cartList.add(cart);
        cart.setRestaurant(this);
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Coupon> couponList = new ArrayList<>();
    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
        coupon.setRestaurant(this);
    }


    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Order> orderList = new ArrayList<>();
    public void addOrder(Order order) {
        orderList.add(order);
        order.setRestaurant(this);
    }

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Serviceability> pincodeList = new ArrayList<>();
    public void addPincode(Serviceability serviceability) {
        pincodeList.add(serviceability);
        serviceability.setRestaurant(this);
    }



}
