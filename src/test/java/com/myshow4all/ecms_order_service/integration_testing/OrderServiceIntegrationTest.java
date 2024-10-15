package com.myshow4all.ecms_order_service.integration_testing;

import com.myshow4all.ecms_order_service.entity.Order;
import com.myshow4all.ecms_order_service.entity.OrderItem;
import com.myshow4all.ecms_order_service.repository.OrderItemRepository;
import com.myshow4all.ecms_order_service.repository.OrderRepository;
import com.myshow4all.ecms_order_service.service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // ensures that the database is rolled back after each test
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void testCreateOrderWithItemsIntegration() {
        // Create an order
        Order order = new Order();
        order.setUserId(123L);
        order.setStatus("PENDING");

        // Create order items
        OrderItem item1 = new OrderItem();
        item1.setProductName("Product 1");
        item1.setQuantity(2);
        item1.setPrice(10.0);

        OrderItem item2 = new OrderItem();
        item2.setProductName("Product 2");
        item2.setQuantity(1);
        item2.setPrice(20.0);

        List<OrderItem> items = Arrays.asList(item1, item2);

        // Save the order and its items
        Order savedOrder = orderService.createOrderWithItems(order, items);

        // Validate the result
        assertNotNull(savedOrder.getId());
        assertEquals(2, savedOrder.getOrderItems().size());
        assertEquals("PENDING", savedOrder.getStatus());
    }
}
