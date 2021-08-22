package in.timesinternet.foodbooking.service.impl;

import in.timesinternet.foodbooking.dto.request.*;
import in.timesinternet.foodbooking.entity.*;
import in.timesinternet.foodbooking.entity.Package;
import in.timesinternet.foodbooking.entity.enumeration.*;
import in.timesinternet.foodbooking.exception.NotFoundException;
import in.timesinternet.foodbooking.repository.*;
import in.timesinternet.foodbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    @Override
    @Transactional
    public Order createOrder(OrderDto orderDto, String userEmail) {
        Customer customer = customerService.getCustomer(userEmail);
        Order order = new Order();
        if (orderDto.getCouponName() != null) {
            ApplyCouponResponseDto applyCouponResponseDto = cartService.addCouponOnCurrentCart(orderDto.getCouponName(), userEmail);
            order.setCoupon(couponService.getCoupon(applyCouponResponseDto.getCouponId()));
            order.setIsCouponApplied(true);
            order.setDiscount(applyCouponResponseDto.getDiscountedValue());
        }

        order.setCustomer(customer);
        order.setAddress(orderDto.getAddress());
        order.setContact(orderDto.getContact());
        order.setType(order.getType());
        order.setTotal(customer.getCurrentCart().getTotal() - order.getDiscount());
        order.setType(orderDto.getOrderType());
        order.setRestaurant(customer.getRestaurant());
        order.setCart(customer.getCurrentCart());
        cartService.addNewCart(userEmail);

        //create and associate payment
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMode(PaymentMode.COD);

        order.setPayment(payment);
        try {
            validateOrder();
            order.setStatus(OrderStatus.PENDING);

        } catch (RuntimeException exception) {
            order.setStatus(OrderStatus.DECLINED);
        }
        order = orderRepository.save(order);
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
            default:
                throw new RuntimeException("invalid request");
        }
    }

    @Transactional
    Order declineOrder(Order order) {
        //update current cart
        Customer customer = order.getCustomer();
        CartDto cartDto= new CartDto();
        List<CartItemDto> cartItemDtoList= new ArrayList<>();
        for(CartItem cartItem:order.getCart().getCartItemList())
            cartItemDtoList.add(new CartItemDto(cartItem.getItem().getId(), cartItem.getQuantity()));
        cartDto.setCartItemList(cartItemDtoList);
        cartService.updateCart(cartDto,customer.getEmail());
        //set order declined
        order.setStatus(OrderStatus.DECLINED);
        return orderRepository.save(order);
    }


    private Order packOrder(Order order) {
        return null;
    }

    @Transactional
    Order approveOrder(Order order) {
        //set order accepted
        order.setStatus(OrderStatus.APPROVED);

        //create package
        Package pack = new Package();
        pack.setOrder(order);
        order.setPack(pack);
        pack.setStatus(PackageStatus.NOT_READY);
        //if order delivery

        if (order.getType().equals(OrderType.DELIVERY)) {
            //create package delivery
            //associate package_delivery and package

            PackageDelivery packageDelivery = new PackageDelivery();
            packageDelivery.setPack(pack);
            pack.addPackageDelivery(packageDelivery);
            packageDelivery.setStatus(PackageDeliveryStatus.UNASSIGNED);
            PackageDelivery packageDeliverySaved=packageDeliveryRepository.save(packageDelivery);
             Package packSaved=packageRepository.save(pack);
            order=orderRepository.save(order);
            Runnable assignDeliveryBoy = () -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DeliveryBoy deliveryBoy = deliveryBoyRepository.findAll().get(0);
                DeliveryPartner deliveryPartner = deliveryPartnerRepositiory.findAll().get(0);
                packageDeliverySaved.setDeliveryPartner(deliveryPartner);
                packageDeliverySaved.setStatus(PackageDeliveryStatus.ASSIGNED);
               InHousePackageDeliveryDetail inHousePackageDeliveryDetail = new InHousePackageDeliveryDetail();
               inHousePackageDeliveryDetail.setDeliveryBoy(deliveryBoy);
               deliveryBoy.addInHousePackageDeliveryDetail(inHousePackageDeliveryDetail);
               packageDelivery.setPackageDeliveryDetail(inHousePackageDeliveryDetail);
                packageRepository.save(packSaved);
                packageDeliveryRepository.save(packageDeliverySaved);
            };
            //schedule different thread to assign delivery boy
            Thread assignDeliveryBoyThread = new Thread(assignDeliveryBoy);
            assignDeliveryBoyThread.start();
        }
        return order;
    }


    private Order cancelOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrder(Integer orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent())
            return orderOptional.get();
        throw new NotFoundException("order not found with id " + orderId);
    }

    private void validateOrder() throws RuntimeException {
        //throw exception is invalid order request
    }

}
