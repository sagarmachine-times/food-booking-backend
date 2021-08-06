package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Image {

    public Image(String mainUrl, String thumbnailUrl, String deleteUrl) {
        this.mainUrl = mainUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.deleteUrl = deleteUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String mainUrl;

    @Column(nullable = false)
    String thumbnailUrl;

    @Column(nullable = false)
    @JsonIgnore
    String deleteUrl;

    @OneToOne(mappedBy = "logo")
    Restaurant restaurantDetail;

}
