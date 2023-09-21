package com.ashitha.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.model.OrderItem;

@Service
public interface OrderItemService {

	public OrderItem createOrderItem(OrderItem orderItem);
}
