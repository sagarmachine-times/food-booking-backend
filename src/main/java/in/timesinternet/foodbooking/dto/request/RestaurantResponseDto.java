package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.RestaurantStatus;
import lombok.*;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestaurantResponseDto {

    private Integer id;
    private RestaurantStatus status;
    private Date openingTime;
    private Date closingTime;
    private String name;
    private String email;
    private String subDomain;
    private Address address;
    private Image logo;
}
