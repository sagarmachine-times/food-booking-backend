package in.timesinternet.foodbooking.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AddressContact{

    String line1;
    String line2;
    Integer pincode;
    String city;
    String state;
    String contactEmail;
    String contactNumber;
    String contactFirstName;
    String contactLastName;
}
