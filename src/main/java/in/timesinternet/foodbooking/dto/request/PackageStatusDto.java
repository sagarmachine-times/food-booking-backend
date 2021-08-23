package in.timesinternet.foodbooking.dto.request;

import in.timesinternet.foodbooking.entity.enumeration.PackageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PackageStatusDto {

    Integer packageId;
    PackageStatus packageStatus;
}
