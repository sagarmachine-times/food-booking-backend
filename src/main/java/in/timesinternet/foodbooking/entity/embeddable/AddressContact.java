package in.timesinternet.foodbooking.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AddressContact{
    @NotNull(message = "address can't be null")
    String line1;
    String line2;
    @NotNull(message = "pincode can't be null")
    @Max(value = 6,message = "pincode length is greater than 6")
    @Min(value = 6,message = "pincode length is lesser than 6")
    Integer pincode;
    @NotNull(message = "city can't be null")
    String city;
    @NotNull(message = "state can't be null")
    String state;
    @Email(message = "invalid email")
    @NotNull(message = "email can't be null")
    String contactEmail;
    @NotNull(message = "contact number can't be null")
    @Max(value = 10,message = "contact number length is greater than 10")
    @Min(value = 10,message = "contact number length is lesser than 10")
    String contactNumber;
    @NotNull(message = "firstname can't be null")
    String contactFirstName;
    @NotNull(message = "lastname can't be null")
    String contactLastName;
}
