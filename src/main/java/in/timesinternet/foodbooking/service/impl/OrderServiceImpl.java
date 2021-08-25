package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.*;
import in.timesinternet.foodbooking.entity.*;
import in.timesinternet.foodbooking.entity.Package;
import in.timesinternet.foodbooking.entity.enumeration.*;
import in.timesinternet.foodbooking.exception.InvalidRequestException;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.repository.*;
import in.timesinternet.foodbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.timesinternet.foodbooking.dto.request.ApplyCouponResponseDto;
import in.timesinternet.foodbooking.dto.request.OrderDto;
import in.timesinternet.foodbooking.entity.Customer;
import in.timesinternet.foodbooking.entity.Order;
import in.timesinternet.foodbooking.entity.Payment;
import in.timesinternet.foodbooking.entity.enumeration.OrderStatus;
import in.timesinternet.foodbooking.entity.enumeration.PaymentMode;
import in.timesinternet.foodbooking.entity.enumeration.PaymentStatus;
import in.timesinternet.foodbooking.repository.OrderRepository;
import in.timesinternet.foodbooking.service.CartService;
import in.timesinternet.foodbooking.service.CouponService;
import in.timesinternet.foodbooking.service.CustomerService;
import in.timesinternet.foodbooking.service.OrderService;

@Service
 public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CouponService couponService;

    @Autowired
    CartService cartService;

    @Autowired
    StaffService staffService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    DeliveryBoyRepository deliveryBoyRepository;

    @Autowired
    DeliveryPartnerRepositiory deliveryPartnerRepositiory;

    @Autowired
    PackageDeliveryRepository packageDeliveryRepository;

    @Autowired
    PackageDeliveryDetailRepository packageDeliveryDetailRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    PincodeService pincodeService;

    @Autowired
    PackageService packageService;
    @Autowired
    RestaurantService restaurantService;

    @Override
    @Transactional
    public Order createOrder(OrderDto orderDto, String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setContact(orderDto.getContact());
        if (order.getAddress().getPincode() == null)
            throw new InvalidRequestException("pincode is required :)");
        if (orderDto.getCouponName() != null) {
            ApplyCouponResponseDto applyCouponResponseDto = cartService.addCouponOnCurrentCart(userEmail,orderDto.getCouponName());
            order.setCoupon(couponService.getCoupon(applyCouponResponseDto.getCouponId()));
            order.setIsCouponApplied(true);
            order.setDiscount(applyCouponResponseDto.getDiscount());
        }
        Integer pincode = order.getAddress().getPincode();
        Serviceability serviceability = null;
        try {
            serviceability = pincodeService.getPincode(pincode, customer.getRestaurant().getId());
        } catch (NotFoundException ex) {
            throw new InvalidRequestException("pincode " + pincode + " is not servicable");
        }
        order.setDeliveryCharge(serviceability.getDeliveryCharge());
        order.setTotal((customer.getCurrentCart().getTotal() + order.getDeliveryCharge()) - order.getDiscount());
        order.setCustomer(customer);

        order.setType(order.getType());
        order.setTotal(customer.getCurrentCart().getTotal() - order.getDiscount());
        order.setType(orderDto.getOrderType());
        order.setRestaurant(customer.getRestaurant());
        order.setCart(customer.getCurrentCart());


        //create and associate payment
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMode(PaymentMode.COD);

        order.setPayment(payment);
//        try {
        validateOrder();
        order.setStatus(OrderStatus.PENDING);
        cartService.addNewCart(userEmail);

//        } catch (RuntimeException exception) {
//            order.setStatus(OrderStatus.DECLINED);
//            cartService.updateCartStatus(CartStatus.MUTABLE, userEmail);
//        }
        order = orderRepository.save(order);
        order.getNext().add(OrderStatus.DECLINED.toString());
        order.getNext().add(OrderStatus.APPROVED.toString());
//        order.getNext().add(OrderStatus.CANCELED.toString());

        return order;
    }

    @Override
    @Transactional
    public Order updateOrderStatus(OrderStatusDto orderStatusDto, String email) {
        Order order = getOrder(orderStatusDto.getOrderId());
        Staff staff = staffService.getStaff(email);
        if (!order.getRestaurant().getId().equals(staff.getRestaurant().getId()))
            throw new RuntimeException("Unauthorized request");
        switch (orderStatusDto.getOrderStatus()) {
            case PACKED:
                return packOrder(order);
            case DECLINED:
                return declineOrder(order);
            case APPROVED:
                return approveOrder(order);
            case CANCELED:
                return cancelOrder(order);
            case PREPARING:
                return preparingOrder(order);
            default:
                throw new InvalidRequestException("invalid request status " + orderStatusDto.getOrderStatus().toString() + " doesn't exist");
        }
    }

    @Transactional
    Order preparingOrder(Order order) {
        if (!order.getStatus().equals(OrderStatus.APPROVED))
            throw new InvalidRequestException("invalid request order can't be preparing since it is" + order.getStatus().toString());
        order.setStatus(OrderStatus.PREPARING);
        order.getNext().add(OrderStatus.PACKED.toString());
        return orderRepository.save(order);
    }

    @Transactional
    Order declineOrder(Order order) {
        System.out.println("DELEITING---------");
        if (!order.getStatus().equals(OrderStatus.PENDING))
            throw new InvalidRequestException("invalid request order can't be decline since it is" + order.getStatus().toString());

        //update current cart
        Customer customer = order.getCustomer();
        CartDto cartDto = new CartDto();
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for (CartItem cartItem : order.getCart().getCartItemList())
            cartItemDtoList.add(new CartItemDto(cartItem.getItem().getId(), cartItem.getQuantity()));
        cartDto.setCartItemList(cartItemDtoList);
        cartService.updateCart(cartDto, customer.getEmail());
        //set order declined
        order.setStatus(OrderStatus.DECLINED);
        return orderRepository.save(order);
    }


    @Transactional
     Order packOrder(Order order) {

        if (order.getStatus().equals(OrderStatus.APPROVED)||order.getStatus().equals(OrderStatus.PREPARING)) {
            order.setStatus(OrderStatus.PACKED);
            PackageStatusDto packageStatusDto = new PackageStatusDto();
            packageStatusDto.setPackageStatus(PackageStatus.READY);
            packageStatusDto.setPackageId(order.getPack().getId());

            Package pack = packageService.updatePackageStatus(packageStatusDto);
            order.setPack(pack);
            return orderRepository.save(order);

        } else {
            throw new InvalidRequestException("order can't be packed as it is "+order.getStatus().toString());
        }
    }

    @Transactional
    Order approveOrder(Order order) {

        if (!order.getStatus().equals(OrderStatus.PENDING))
            throw new InvalidRequestException("invalid request order can't be approved since it is" + order.getStatus().toString());

        //set order accepted
        order.setStatus(OrderStatus.APPROVED);

        //create package
        Package pack = new Package();
        pack.setOrder(order);
        order.setPack(pack);
        pack.setStatus(PackageStatus.NOT_READY);
        pack.getNext().add(PackageStatus.READY.toString());
        //if order delivery

        if (order.getType().equals(OrderType.DELIVERY)) {
            //create package delivery
            //associate package_delivery and package

            PackageDelivery packageDelivery = new PackageDelivery();
            packageDelivery.setPack(pack);
            pack.addPackageDelivery(packageDelivery);
            pack.setCurrentPackageDelivery(packageDelivery);
            packageDelivery.setStatus(PackageDeliveryStatus.UNASSIGNED);
            PackageDelivery packageDeliverySaved = packageDeliveryRepository.save(packageDelivery);
            packageRepository.save(pack);
            order = orderRepository.save(order);
            Runnable assignDeliveryBoy = () -> {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                DeliveryBoy deliveryBoy = deliveryBoyRepository.findAll().get(0);
                DeliveryPartner deliveryPartner = deliveryPartnerRepositiory.findAll().get(0);
                packageDeliverySaved.setDeliveryPartner(deliveryPartner);
                packageDeliverySaved.setStatus(PackageDeliveryStatus.ASSIGNED);
                InHousePackageDeliveryDetail inHousePackageDeliveryDetail = new InHousePackageDeliveryDetail();
                inHousePackageDeliveryDetail.setDeliveryBoy(deliveryBoy);
                deliveryBoy.addInHousePackageDeliveryDetail(inHousePackageDeliveryDetail);
                packageDelivery.setPackageDeliveryDetail(inHousePackageDeliveryDetail);

//                packageRepository.save(packSaved);
//                packageDeliveryRepository.save(packageDeliverySaved);
            };
            //schedule different thread to assign delivery boy
            Thread assignDeliveryBoyThread = new Thread(assignDeliveryBoy);
            assignDeliveryBoyThread.start();
            try {
                assignDeliveryBoyThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        order.getNext().add(OrderStatus.PACKED.toString());
        order.getNext().add(OrderStatus.PREPARING.toString());
//        order.getNext().add(OrderStatus.CANCELED.toString());

        return orderRepository.findById(order.getId()).get();
    }


    @Transactional
     Order cancelOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrder(Integer orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent())
            return orderOptional.get();
        throw new NotFoundException("order not found with id " + orderId);
    }

    @Transactional
     void validateOrder() throws RuntimeException {
        //throw exception is invalid order request
    }

    @Override
    @Transactional
    public Order updateOrder(UpdateOrderDto updateOrderDto, String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        Order order = getOrder(updateOrderDto.getOrderId());

        order.setAddress(updateOrderDto.getAddress());
        order.setContact(updateOrderDto.getContact());
        return order;
    }

    @Override
    public List<Order> getAllOrder(String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        return customer.getOrderList();
    }

    @Override
    public List<Order> getAllOrderByRestaurant(Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        return restaurant.getOrderList();
    }

    @Override
    @Transactional
    public Order completeOrder(Integer orderId) {

        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.COMPLETED);
        order.getPayment().setStatus(PaymentStatus.PAID);
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrderByCustomer(Integer orderId, String email)
    {
        Customer customer = customerService.getCustomer(email);
        Order order = getOrder(orderId);

        if (order.getStatus().equals(OrderStatus.PENDING) || order.getStatus().equals(OrderStatus.APPROVED))
        {
            order.setStatus(OrderStatus.CANCELED);
            PackageDelivery packageDelivery = order.getPack().getCurrentPackageDelivery();
            packageDelivery.setStatus(PackageDeliveryStatus.CANCELED);
            order.getPack().setCurrentPackageDelivery(packageDelivery);
            packageDeliveryRepository.save(packageDelivery);

            return orderRepository.save(order);
        }
        else
        {
            throw new InvalidRequestException(" Your order cannot be cancelled. Your order is in following stage :- "+order.getStatus());
        }
    }
}
