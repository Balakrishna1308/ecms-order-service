package com.myshow4all.ecms_order_service.controller;

import com.myshow4all.ecms_order_service.entity.Order;
import com.myshow4all.ecms_order_service.exception.OrderNotFoundException;
import com.myshow4all.ecms_order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOder(@RequestBody Order order)
    {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> fetchOrderByOrderId(@PathVariable Long orderId)
    {
        Optional<Order> order = orderService.getOrderByOrderId(orderId);
        if (order.isPresent())
        {
            Order existingOrder = order.get();
            return new ResponseEntity<>(existingOrder, HttpStatus.OK);
        }
        else
        {
            throw new OrderNotFoundException("Order with id "+orderId+" not found.");
        }
    }


    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
                                                   @RequestParam String status)
    {
        Optional<Order> order = orderService.getOrderByOrderId(orderId);
        if (order.isPresent())
        {
            Order existingOrder = order.get();
            existingOrder.setStatus(status);
            Order updaetdOrder = orderService.createOrder(existingOrder);
            return new ResponseEntity<>(updaetdOrder, HttpStatus.OK);
        }
        else
        {
            throw new OrderNotFoundException("Order with id "+orderId+" not present");
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId)
    {
        Optional<Order> order = orderService.getOrderByOrderId(orderId);

        if (order.isPresent())
        {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok("Order deleted successfully");
        }
        else
        {
            throw new OrderNotFoundException("Order with id "+" not found");
        }
    }
}
