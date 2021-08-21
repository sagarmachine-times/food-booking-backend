package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.OrderDto;
import in.timesinternet.foodbooking.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto orderDto, String userEmail);

    List<Order> getAllOrdersOfCustomerForRestaurant(String userEmail);
}
