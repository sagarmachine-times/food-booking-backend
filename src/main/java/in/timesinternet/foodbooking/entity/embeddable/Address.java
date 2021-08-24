package in.timesinternet.foodbooking.entity.embeddable;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class Address {
    String line1;
    String line2;
    Integer pincode;
    String city;
    String state;

}
