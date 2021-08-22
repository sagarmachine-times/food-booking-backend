package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    @OneToMany(mappedBy = "deliveryPartner")
    @JsonIgnore
    List<PackageDelivery> packageDeliveryList = new ArrayList<>();

    public void addPackageDelivery(PackageDelivery packageDelivery) {
        packageDelivery.setDeliveryPartner(this);
        packageDeliveryList.add(packageDelivery);
    }
}
