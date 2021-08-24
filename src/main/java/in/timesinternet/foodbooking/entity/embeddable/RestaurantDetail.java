package in.timesinternet.foodbooking.entity.embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;
import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.enumeration.RestaurantStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class RestaurantDetail {

    @Column(unique = true, updatable = false)
    String name;

    @Column(unique = true)
    String email;

    @Column(unique = true, updatable = false)
    String subDomain;

    @JsonFormat( pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    Date openingTime;

    @JsonFormat( pattern = "hh:mm:ss")
    @Temporal(TemporalType.TIME)
    Date closingTime;

    @Embedded
    Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RestaurantStatus status = RestaurantStatus.OPEN;


}
