package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.Image;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.RestaurantStatus;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat( pattern = "hh:mm:ss")
    private Date openingTime;

    @JsonFormat( pattern = "hh:mm:ss")
    private Date closingTime;

    private String name;
    private String email;
    private String subDomain;
    private Address address;
    private Image logo;
}
