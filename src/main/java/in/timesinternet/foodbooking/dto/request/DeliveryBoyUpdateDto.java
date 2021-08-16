package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.embeddable.Address;
import in.timesinternet.foodbooking.entity.embeddable.Contact;
import in.timesinternet.foodbooking.entity.enumeration.DeliveryBoyStatus;

import javax.validation.constraints.NotNull;

public class DeliveryBoyUpdateDto {

    @NotNull(message = "first name can't be null")
    String firstName;

    @NotNull(message = "last name can't be null")
    String lastName;

    Address address;
    Contact contact;
    DeliveryBoyStatus deliveryBoyStatus;
}
