package com.myshow4all.ecms_order_service.unit_testing;

import com.myshow4all.ecms_order_service.entity.Order;
import com.myshow4all.ecms_order_service.entity.OrderItem;
import com.myshow4all.ecms_order_service.repository.OrderItemRepository;
import com.myshow4all.ecms_order_service.repository.OrderRepository;
import com.myshow4all.ecms_order_service.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Test
    public void testCreateOrderWithItems() {
        // Given
        Order order = new Order();
        order.setId(1L);
        order.setUserId(123L);
        order.setStatus("PENDING");

        OrderItem item1 = new OrderItem();
        item1.setProductName("Product 1");
        item1.setPrice(10.00);
        item1.setQuantity(2);

        OrderItem item2 = new OrderItem();
        item2.setProductName("Product 2");
        item2.setPrice(20.00);
        item2.setQuantity(1);

        List<OrderItem> items = Arrays.asList(item1, item2);

        // Mock repository behavior
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderItemRepository.saveAll(items)).thenReturn(items);

        // When
        Order savedOrder = orderService.createOrderWithItems(order, items);

        // Then
        assertNotNull(savedOrder);
        assertEquals(2, savedOrder.getOrderItems().size());
        assertEquals("PENDING", savedOrder.getStatus());
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
        Mockito.verify(orderItemRepository, Mockito.times(1)).saveAll(items);
    }
}
