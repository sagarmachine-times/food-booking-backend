package in.timesinternet.foodbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CouponDto {
    private String name;
    private Integer value;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date startingDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date endingDate;

    private Integer minimumCartValue;
    private Integer maxDiscount;
    private Integer maxPerUser;
    private Integer totalUse;
    private String termsAndCondition;
    private Integer imageId;
}
