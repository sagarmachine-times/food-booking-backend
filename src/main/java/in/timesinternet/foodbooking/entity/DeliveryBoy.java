package in.timesinternet.foodbooking.entity;


import in.timesinternet.foodbooking.entity.enumeration.DeliveryBoyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoy extends  User {

    @Enumerated(EnumType.STRING)
    DeliveryBoyStatus deliveryBoyStatus=DeliveryBoyStatus.AVAILABLE;

    //relationship
    @OneToMany
    List<InHousePackageDeliveryDetail> inHousePackageDeliveryDetailList= new ArrayList<>();
    public void addInHousePackageDeliveryDetail(InHousePackageDeliveryDetail inHousePackageDeliveryDetail){
        inHousePackageDeliveryDetail.setDeliveryBoy(this);
        inHousePackageDeliveryDetailList.add(inHousePackageDeliveryDetail);
    }

}
