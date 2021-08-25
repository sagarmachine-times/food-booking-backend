package in.timesinternet.foodbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.RestaurantStatus;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateDto {

    Address address;
    RestaurantStatus restaurantStatus = RestaurantStatus.OPEN;

    @JsonFormat(pattern = "hh:mm:ss")
    Date openingTime;

    @JsonFormat(pattern = "hh:mm:ss")
    Date closingTime;

}
