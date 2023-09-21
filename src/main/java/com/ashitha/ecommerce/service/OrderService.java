package com.ashitha.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.OrderException;
import com.ashitha.ecommerce.model.Address;
import com.ashitha.ecommerce.model.Orders;
import com.ashitha.ecommerce.model.User;


public interface OrderService {
	
	
	public Orders createOrder(User user,Address shippingAdress);
	
	public Orders findOrderById(Long orderId) throws OrderException;
	
	public List<Orders> usersOrderHistory(Long userId);
	
	public Orders placedOrder(Long orderId) throws OrderException;
	
	public Orders confirmedOrder(Long orderId) throws OrderException;
	
	public Orders shippedOrder(Long orderId) throws OrderException;
	
	public Orders deliveredOrder(Long orderId) throws OrderException;
	
	public Orders canceledOrder(Long orderId) throws OrderException;
	
	public List<Orders> getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;

}
