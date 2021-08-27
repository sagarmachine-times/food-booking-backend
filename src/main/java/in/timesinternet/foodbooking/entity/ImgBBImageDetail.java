package in.timesinternet.foodbooking.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImgBBImageDetail extends ImageDetail{

    @Column(nullable = false)
    String mainUrl;

    @Column(nullable = false)
    String thumbnailUrl;

    @Column(nullable = false)
    @JsonIgnore
    String deleteUrl;

}
