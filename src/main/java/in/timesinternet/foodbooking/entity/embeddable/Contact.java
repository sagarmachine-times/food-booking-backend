package in.timesinternet.foodbooking.entity.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    String email;
    String number;
    String firstName;
    String lastName;
}
