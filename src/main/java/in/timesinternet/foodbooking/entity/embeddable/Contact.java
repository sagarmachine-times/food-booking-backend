package in.timesinternet.foodbooking.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact {
    String contactEmail;
    String contactNumber;
    String contactFirstName;
    String contactLastName;
}
