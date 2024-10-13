package com.myshow4all.ecms_order_service.service.impl;

import com.myshow4all.ecms_order_service.entity.Order;
import com.myshow4all.ecms_order_service.exception.OrderNotFoundException;
import com.myshow4all.ecms_order_service.repository.OrderRepository;
import com.myshow4all.ecms_order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent())
        {
            Order order = existingOrder.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        else
        {
            throw new OrderNotFoundException("Order with id "+orderId+" not found.");
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
