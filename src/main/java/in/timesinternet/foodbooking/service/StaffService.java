package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.StaffDto;
import in.timesinternet.foodbooking.dto.request.StaffUpdateDto;
import in.timesinternet.foodbooking.entity.Staff;

import java.util.List;

public interface StaffService {

    List<Staff> createStaff(Integer restaurantId, StaffDto staffDto);

    List<Staff> getStaffList(Integer restaurantId);

    Staff updateStaff(String userEmail, StaffUpdateDto staffUpdateDto);

    Staff deleteStaff(String userEmail, Integer staffId);

    Staff getStaff(Integer staffId);
    Staff getStaff(String email);
}
