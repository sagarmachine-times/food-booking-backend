package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.OrderDto;
import in.timesinternet.foodbooking.dto.request.OrderStatusDto;
import in.timesinternet.foodbooking.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto orderDto, String userEmail);

    Order updateOrderStatus(OrderStatusDto orderStatusDto, String email);

    Order getOrder(Integer orderId);

    List<Order> getAllOrdersOfCustomerForRestaurant(String userEmail);
}
