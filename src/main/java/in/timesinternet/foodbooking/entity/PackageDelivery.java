package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.enumeration.OrderStatus;
import in.timesinternet.foodbooking.entity.enumeration.PackageDeliveryStatus;
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
public class PackageDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    @Enumerated(EnumType.STRING)
    PackageDeliveryStatus status;

    //relationship
    @ManyToOne
    DeliveryPartner deliveryPartner;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    Package pack;

    @OneToOne(cascade = CascadeType.ALL)
    PackageDeliveryDetail packageDeliveryDetail;


    @Transient
    List<String> next = new ArrayList<>();

    @PostLoad
    void populateNext() {
        switch (status) {
            case ON_THE_WAY_TO_PICK:next.add(PackageDeliveryStatus.PICKED.toString());
                next.add(PackageDeliveryStatus.ON_THE_WAY_TO_DROP.toString());
                break;
            case PICKED:next.add(PackageDeliveryStatus.ON_THE_WAY_TO_DROP.toString());
                next.add(PackageDeliveryStatus.DELIVERED.toString());
                break;
            case ON_THE_WAY_TO_DROP:next.add(PackageDeliveryStatus.DELIVERED.toString());
        }
    }
}
