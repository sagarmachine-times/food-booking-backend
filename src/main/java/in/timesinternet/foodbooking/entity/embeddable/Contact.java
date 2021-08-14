package in.timesinternet.foodbooking.entity.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    String contactEmail;
    String contactNumber;
    String contactFirstName;
    String contactLastName;
}
