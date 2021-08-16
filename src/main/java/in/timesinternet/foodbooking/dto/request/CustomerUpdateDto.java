package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;

import javax.validation.constraints.NotNull;

public class CustomerUpdateDto {


    @NotNull(message = "firstName can't be null")
    String firstName;

    @NotNull(message = "lastName can't be null")
    String lastName;


    Address address;
    Contact contact;
}
