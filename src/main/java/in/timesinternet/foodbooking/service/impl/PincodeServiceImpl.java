package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.contoller.dto.request.PincodeDto;
import in.timesinternet.foodbooking.entity.Category;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.repository.ServiceabilityRepository;
import in.timesinternet.foodbooking.service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PincodeServiceImpl implements PincodeService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    ServiceabilityRepository serviceabilityRepository;

    @Override
    public Serviceability addPincode(PincodeDto pincodeDto, Integer restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent())
        {
            Restaurant restaurant=optionalRestaurant.get();
            Serviceability serviceability=new Serviceability();
            serviceability.setDeliveryCharge(100);
            serviceability.setPincode(pincodeDto.getPincode());
            restaurant.addPincode(serviceability);

           return serviceabilityRepository.save(serviceability);
        }
        else
        {
            throw new RuntimeException("This Restaurant does not exist");
        }


//restaurant.getpincode();


    }
}
