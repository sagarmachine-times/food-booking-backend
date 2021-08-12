package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.contoller.dto.request.PincodeDto;
import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.entity.Serviceability;
import io.swagger.models.auth.In;

import java.util.List;

public interface PincodeService {
    List<Serviceability> addPincode(List<PincodeDto>pincodeDto, Integer restaurantId);
    List<Serviceability> getPincode(Integer restaurantId);
    Serviceability deletePincode(Integer picodeId, Integer restaurantId);
    Serviceability updatePincode(AvalibilityDto avalibilityDto, Integer restaurantId);
}
