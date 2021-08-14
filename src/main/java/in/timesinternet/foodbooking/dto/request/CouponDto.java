package in.timesinternet.foodbooking.dto.request;

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
    private Date startingDate;
    private Date endingDate;
    private Integer minimumCartValue;
    private Integer maxDiscount;
    private Integer maxPerUser;
    private Integer totalUse;
    private String termsAndCondition;
    private Integer imageId;
}
