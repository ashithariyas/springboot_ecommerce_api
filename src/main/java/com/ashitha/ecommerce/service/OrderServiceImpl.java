package com.ashitha.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.OrderException;
import com.ashitha.ecommerce.model.Address;
import com.ashitha.ecommerce.model.Orders;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.repository.CartRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartService cartItemService;
	
	@Autowired
	private ProductService productService;

	@Override
	public Orders createOrder(User user, Address shippingAdress) {

		return null;
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderException {

		return null;
	}

	@Override
	public List<Orders> usersOrderHistory(Long userId) {

		return null;
	}

	@Override
	public Orders placedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders confirmedOrder(Long orderId) throws OrderException {

		return null;
	}

	@Override
	public Orders shippedOrder(Long orderId) throws OrderException {

		return null;
	}

	@Override
	public Orders deliveredOrder(Long orderId) throws OrderException {

		return null;
	}

	@Override
	public Orders canceledOrder(Long orderId) throws OrderException {

		return null;
	}

	@Override
	public List<Orders> getAllOrders() {

		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {

		
	}

}
