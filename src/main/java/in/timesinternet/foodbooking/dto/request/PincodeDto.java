package in.timesinternet.foodbooking.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PincodeDto {

    @NotNull(message = "Pincode can't be null")
    private int pincode;
}
