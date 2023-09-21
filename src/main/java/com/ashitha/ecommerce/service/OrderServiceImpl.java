package com.ashitha.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.OrderException;
import com.ashitha.ecommerce.model.Address;
import com.ashitha.ecommerce.model.Cart;
import com.ashitha.ecommerce.model.CartItem;
import com.ashitha.ecommerce.model.OrderItem;
import com.ashitha.ecommerce.model.Orders;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.repository.AddressRepository;
import com.ashitha.ecommerce.repository.CartRepository;
import com.ashitha.ecommerce.repository.OrderItemRepository;
import com.ashitha.ecommerce.repository.OrderRepository;
import com.ashitha.ecommerce.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public Orders createOrder(User user, Address shippingAdress) {
		shippingAdress.setUser(user);
		Address address = addressRepository.save(shippingAdress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem item: cart.getCartItems()) {
			
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
		
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			orderItems.add(createdOrderItem);
		}
		
		Orders createdOrder = new Orders();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Orders savedOrder = orderRepository.save(createdOrder);	
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		return savedOrder;
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderException {
         Optional<Orders> opt=orderRepository.findById(orderId);       
         if(opt.isPresent()) {
        	 return opt.get();
         }
         throw new OrderException("Order not exist with id "+orderId);
	}

	@Override
	public List<Orders> usersOrderHistory(Long userId) {
		List<Orders> orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Orders placedOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return order;
	}

	@Override
	public Orders confirmedOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Orders shippedOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Orders deliveredOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Orders canceledOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}
}
