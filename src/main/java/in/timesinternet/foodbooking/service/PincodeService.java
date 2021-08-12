package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.contoller.dto.request.PincodeDto;
import in.timesinternet.foodbooking.entity.Serviceability;

public interface PincodeService {
    Serviceability addPincode(PincodeDto pincodeDto, Integer restaurantId);
}
