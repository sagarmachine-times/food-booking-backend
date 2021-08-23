package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.enumeration.PackageDeliveryStatus;
import in.timesinternet.foodbooking.entity.enumeration.PackageStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageDeliveryDto {

    Integer packageDeliveryId;
    PackageDeliveryStatus packageDeliveryStatus;
}
