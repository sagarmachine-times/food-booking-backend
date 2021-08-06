package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.RestaurantDetail;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @Embedded
    RestaurantDetail restaurantDetail;

    //relationship
    @OneToOne(cascade = CascadeType.ALL)
    Image logo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Staff> staffList = new ArrayList<>();

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    public void addStaff(Staff staff) {
        staffList.add(staff);
        staff.setRestaurant(this);
    }

}
