package com.myshow4all.ecms_order_service.repository;

import com.myshow4all.ecms_order_service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
