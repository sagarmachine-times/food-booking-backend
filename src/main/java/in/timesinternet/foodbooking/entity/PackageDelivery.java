package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import in.timesinternet.foodbooking.entity.embeddable.Next;
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

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    Date createdAt;

    @JsonFormat( pattern = "dd-MM-yyyy hh:mm:ss")
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
    List<Next> next = new ArrayList<>();

    @Transient
    String stage;

    @PostLoad
   public void populateNext() {
        next= new ArrayList<>();
        switch (status) {
            case ASSIGNED:
                next.add(new Next("ON_THE_WAY_TO_PICK", PackageDeliveryStatus.ON_THE_WAY_TO_PICK.toString()));
                break;
            case ON_THE_WAY_TO_PICK:
                next.add(new Next("Picked", PackageDeliveryStatus.PICKED.toString()));
                break;
            case PICKED:
                next.add(new Next("ON_THE_WAY_TO_DROP", PackageDeliveryStatus.ON_THE_WAY_TO_DROP.toString()));
                break;
            case ON_THE_WAY_TO_DROP:
                next.add(new Next("ON_THE_WAY_TO_DROP", PackageDeliveryStatus.DELIVERED.toString()));
        }
        switch (status) {
            case ASSIGNED:stage="ASSIGNED";break;
            case DELIVERED:stage="DELIVERED";break;
            case CANCELED:stage="CANCELED";break;
            case ON_THE_WAY_TO_DROP:stage="Picking Up Order";break;
            case ON_THE_WAY_TO_PICK:stage="Dropping Order";break;
            case PICKED:stage="PICKED";break;
            case UNASSIGNED:stage="UNASSIGNED";break;
        }
    }
}
