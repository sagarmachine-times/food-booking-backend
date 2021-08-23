package in.timesinternet.foodbooking.dto.request;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCouponDto {

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
