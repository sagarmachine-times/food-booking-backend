package in.timesinternet.foodbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InHousePackageDeliveryDetail extends PackageDeliveryDetail implements Serializable {

    @ManyToOne
    DeliveryBoy deliveryBoy;

}
