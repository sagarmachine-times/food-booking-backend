package in.timesinternet.foodbooking.service;

import in.timesinternet.foodbooking.dto.request.OrderDto;
import in.timesinternet.foodbooking.dto.request.OrderStatusDto;
import in.timesinternet.foodbooking.dto.request.UpdateOrderDto;
import in.timesinternet.foodbooking.entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto orderDto, String userEmail);

    Order updateOrderStatus(OrderStatusDto orderStatusDto, String email);

    Order getOrder(Integer orderId);

    Order updateOrder(UpdateOrderDto updateOrderDto, String userEmail);


    List<Order> getAllOrder(String userEmail);

    List<Order> getAllOrderByRestaurant(Integer restaurantId);

    Order completeOrder(Integer orderId);

    Order cancelOrderByCustomer(Integer orderId, String email);
}
