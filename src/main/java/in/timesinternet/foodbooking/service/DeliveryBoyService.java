package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.DeliveryBoyDto;
import in.timesinternet.foodbooking.dto.request.DeliveryBoyUpdateDto;
import in.timesinternet.foodbooking.entity.DeliveryBoy;

import java.util.List;

public interface DeliveryBoyService {

    DeliveryBoy createDeliveryBoy(DeliveryBoyDto deliveryBoyDto);
    List<DeliveryBoy> getAllDeliveryBoy();

    DeliveryBoy getDeliveryBoy(Integer deliveryBoyId);

    DeliveryBoy deleteDeliveryBoy(Integer deliveryBoyId);


    DeliveryBoy UpdateDeliveryBoy(DeliveryBoyUpdateDto deliveryBoyUpdateDto, Integer deliveryBoyId);

}
