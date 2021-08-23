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

    @NotNull(message = "")
    private String name;
   @Max(value = 100,message = "")
    private Integer value;


    @JsonFormat( pattern = "dd-MM-yyyy")
    @NotNull(message = "Starting Date can't be Null")
    private Date startingDate;

    @JsonFormat( pattern = "dd-MM-yyyy")
    private Date endingDate;

    private Integer minimumCartValue;
    private Integer maxDiscount;
    private Integer maxPerUser;
    private Integer totalUse;
    private String termsAndCondition;
    private Integer imageId;
}
