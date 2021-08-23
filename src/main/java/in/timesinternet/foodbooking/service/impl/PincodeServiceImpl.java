package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.PincodeDto;
import in.timesinternet.foodbooking.dto.request.AvalibilityDto;
import in.timesinternet.foodbooking.entity.Restaurant;
import in.timesinternet.foodbooking.entity.Serviceability;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.exception.UnauthorizedException;
import in.timesinternet.foodbooking.repository.RestaurantRepository;
import in.timesinternet.foodbooking.repository.ServiceabilityRepository;
import in.timesinternet.foodbooking.service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PincodeServiceImpl implements PincodeService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    ServiceabilityRepository serviceabilityRepository;

    @Override
    public List<Serviceability> addPincode(List<PincodeDto> pincodeDto, Integer restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {

            int i = 100;
            Restaurant restaurant = optionalRestaurant.get();
            for (PincodeDto pincodeDto1 : pincodeDto) {

                // Print all elements of ArrayList


                Serviceability serviceability = new Serviceability();
                serviceability.setDeliveryCharge(i);
                serviceability.setPincode(pincodeDto1.getPincode());
                restaurant.addPincode(serviceability);
                serviceabilityRepository.save(serviceability);
                i++;
            }
            return restaurant.getPincodeList();
        } else {
            throw new NotFoundException("This Restaurant is  not found");
        }


        //restaurant.getpincode();
    }


    @Override
    public List<Serviceability> getPincodeList(Integer restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getPincodeList();
        } else {
            throw new NotFoundException("This Restaurant is not found");
        }

    }

    public Serviceability deletePincode(Integer pincodeId, Integer restaurantId) {

        Optional<Serviceability> optionalServiceability = serviceabilityRepository.findById(pincodeId);
        if (optionalServiceability.isPresent()) {
            Serviceability serviceability = optionalServiceability.get();
            Restaurant restaurant = serviceability.getRestaurant();
            if (restaurant.getId() == restaurantId) {
                serviceabilityRepository.deleteById(pincodeId);
                return serviceability;
            } else {
                throw new UnauthorizedException("You are not authorised to delete this Pincode");
            }
        } else {
            throw new RuntimeException(" This pincode Does not exits in your restaurant");
        }

    }
    public Serviceability updatePincode(AvalibilityDto avalibilityDto, Integer restaurantId)
    {   System.out.println(avalibilityDto);
        Optional<Serviceability>  optionalServiceability= serviceabilityRepository.findById(avalibilityDto.getId());
        if (optionalServiceability.isPresent()) {
            Serviceability serviceability = optionalServiceability.get();
            Restaurant restaurant = serviceability.getRestaurant();
            if (restaurant.getId() == restaurantId) {
                serviceability.setIsServiceable(avalibilityDto.getIsAvailable());
                serviceabilityRepository.save(serviceability);
                return serviceability;
            } else {
                throw new UnauthorizedException("You are not authorised to change the Avalibility of pincode");
            }
        } else {
            throw new UnauthorizedException(" You are not authorised");
        }

    }

    @Override
    public Serviceability getPincode(Integer pincode, Integer restaurantId) {
        Optional<Serviceability> serviceabilityOptional = serviceabilityRepository.findByPincodeAndRestaurantId(pincode, restaurantId);
        if(serviceabilityOptional.isPresent())
        return serviceabilityOptional.get();
        throw new NotFoundException("pincode "+pincode+" is not serviceable");
    }

}
