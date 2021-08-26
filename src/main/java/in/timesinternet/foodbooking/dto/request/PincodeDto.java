package in.timesinternet.foodbooking.dto.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PincodeDto {

    @NotNull(message = "pincode can't be null")
    @Max(value =6,message = "pincode length is greater than 6")
    @Min(value = 6,message ="pincode length is less than 6")
    private Integer pincode;
}
