package com.ashitha.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.model.OrderItem;
import com.ashitha.ecommerce.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}
}
