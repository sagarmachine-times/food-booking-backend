package in.timesinternet.foodbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Staff extends User {

    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;

    @Override
    public String toString() {
        return "Staff{" +
                "restaurant=" + restaurant +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", role=" + role +
                '}';
    }
}
