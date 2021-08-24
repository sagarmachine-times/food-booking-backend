package in.timesinternet.foodbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CouponDto {

    @NotNull(message = "name can't be null")
    private String name;
    @Max(value = 100, message = "Maximum value can't more than 100")
    private Integer value;
    @JsonFormat(pattern = "dd-MM-yyyy")

    @NotNull(message = "Starting Date can't be Null")
    private Date startingDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Ending Date can't be Null")
    private Date endingDate;

    @NotNull(message = "max discount can't be null")
    Integer maxDiscount;

    @NotNull(message = "Cart value can't be null")
    private Integer minimumCartValue;
    @NotNull(message = "max per user can't be null")
    private Integer maxPerUser;
    @NotNull(message = "Total use can't be null")
    private Integer totalUse;

    @NotNull(message = "Terms and Conditions can't be null")
    private String termsAndCondition;
    @NotNull(message = "ImageId can't be Null")
    private Integer imageId;
}
