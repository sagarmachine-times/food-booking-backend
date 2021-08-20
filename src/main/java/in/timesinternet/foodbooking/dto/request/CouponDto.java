package in.timesinternet.foodbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CouponDto {

    @Column(unique = true)
    private String name;
    private Integer value;


    @JsonFormat( pattern = "dd-MM-yyyy")
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
