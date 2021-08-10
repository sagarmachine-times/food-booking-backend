package in.timesinternet.foodbooking.entity;


import in.timesinternet.foodbooking.entity.enumeration.PackageDeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne
    Package pack;


}
