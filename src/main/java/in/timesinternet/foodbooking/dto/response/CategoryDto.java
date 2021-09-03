package in.timesinternet.foodbooking.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class CategoryDto {
    Integer id;
    String name;
    Date createdAt;
    Date updatedAt;
    Boolean isAvailable = true;

}
