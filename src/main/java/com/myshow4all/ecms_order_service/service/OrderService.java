package com.myshow4all.ecms_order_service.service;

import com.myshow4all.ecms_order_service.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);
    Optional<Order> getOrderByOrderId(Long orderId);
    List<Order> getOrdersByUserId(Long userId);
    Order updateOrderStatus(Long orderId, String status);
    void deleteOrder(Long orderId);

}
