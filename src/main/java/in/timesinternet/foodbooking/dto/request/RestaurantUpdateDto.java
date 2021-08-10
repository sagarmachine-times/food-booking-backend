package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.RestaurantStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateDto {
    Date openingDate;
    Date closingDate;
    Address address;
    RestaurantStatus status;
}
