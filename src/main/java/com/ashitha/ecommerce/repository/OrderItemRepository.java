package com.ashitha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashitha.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
