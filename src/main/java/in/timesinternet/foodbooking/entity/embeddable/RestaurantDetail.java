package in.timesinternet.foodbooking.entity.embeddable;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Embeddable@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantDetail {

    @Column(unique = true, updatable = false)
    String name;

    @Column(unique = true)
    String email;

    @Column(unique = true,updatable = false)
    String subDomain;

    @Temporal(TemporalType.TIME)
    Date openingDate;

    @Temporal(TemporalType.TIME)
    Date closingDate;

    @Embedded
    Address address;


}
