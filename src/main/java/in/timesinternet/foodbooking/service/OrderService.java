package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.OrderDto;
import in.timesinternet.foodbooking.entity.Order;

public interface OrderService {
    Order createOrder(OrderDto orderDto, String userEmail);
}
