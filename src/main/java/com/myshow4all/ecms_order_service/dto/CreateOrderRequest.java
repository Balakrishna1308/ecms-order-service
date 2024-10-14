package com.myshow4all.ecms_order_service.dto;

import com.myshow4all.ecms_order_service.entity.Order;
import com.myshow4all.ecms_order_service.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateOrderRequest {
    private Order order;
    private List<OrderItem> items;
}
