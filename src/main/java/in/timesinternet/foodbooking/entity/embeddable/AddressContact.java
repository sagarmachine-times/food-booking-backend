package in.timesinternet.foodbooking.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AddressContact{
    String line1;
    String line2;
    String pincode;
    String city;
    String state;
    String contactEmail;
    String contactNumber;
    String contactFirstName;
    String contactLastName;
}
