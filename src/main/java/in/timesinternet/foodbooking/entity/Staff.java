package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Staff extends User implements Serializable {

    @ManyToOne
    Restaurant restaurant;

}
