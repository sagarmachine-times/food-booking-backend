package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.PincodeDto;
import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.entity.Serviceability;

import java.util.List;

public interface PincodeService {
    List<Serviceability> addPincode(List<PincodeDto>pincodeDto, Integer restaurantId);
    List<Serviceability> getPincodeList(Integer restaurantId);
    Serviceability deletePincode(Integer picodeId, Integer restaurantId);
    Serviceability updatePincode(AvalibilityDto avalibilityDto, Integer restaurantId);
    Serviceability getPincode(Integer pincode, Integer restaurantId);
}
