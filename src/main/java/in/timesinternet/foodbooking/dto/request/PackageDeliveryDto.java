package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.enumeration.PackageDeliveryStatus;
import in.timesinternet.foodbooking.entity.enumeration.PackageStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageDeliveryDto {

    @NotNull(message = "packageDeliveryId can't be null")
    Integer packageDeliveryId;

    @NotNull(message = "packageDeliveryStatus can't be null")
    PackageDeliveryStatus packageDeliveryStatus;
}
