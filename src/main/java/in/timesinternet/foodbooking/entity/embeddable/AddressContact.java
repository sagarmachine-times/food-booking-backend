package in.timesinternet.foodbooking.entity.embeddable;

import javax.persistence.Embeddable;

@Embeddable
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
