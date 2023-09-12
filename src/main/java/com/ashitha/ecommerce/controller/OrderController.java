package com.ashitha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashitha.ecommerce.exception.OrderException;
import com.ashitha.ecommerce.exception.UserException;
import com.ashitha.ecommerce.model.Address;
import com.ashitha.ecommerce.model.Orders;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.service.OrderService;
import com.ashitha.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public ResponseEntity<Orders> createOrder(@RequestBody Address shippingAddress,@RequestHeader("Authorization")String jwt) throws UserException{
		
		User user = userService.findUserProfieByJwt(jwt);	
		Orders order = orderService.createOrder(user, shippingAddress);	
		System.out.println("Order" +order);	
		return new ResponseEntity<Orders>(order,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<List<Orders>>usersOrderHistory(@RequestHeader("Authorization")String jwt) throws UserException{
		
		User user = userService.findUserProfieByJwt(jwt);
		List<Orders> orders = orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Orders>findOrderById(@PathVariable("Id")Long orderId,@RequestHeader("Authorization")String jwt) throws UserException,OrderException{
		
		User user = userService.findUserProfieByJwt(jwt);
		Orders order = orderService.findOrderById(orderId);
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
	}
}
