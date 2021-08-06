package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.StaffDto;

public interface StaffService {

    Object createStaff(Integer restaurantId, StaffDto staffDto);

}
