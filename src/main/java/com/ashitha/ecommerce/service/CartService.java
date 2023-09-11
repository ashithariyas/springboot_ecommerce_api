package com.ashitha.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.model.Cart;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.request.AddItemRequest;

@Service
public interface CartService {

	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
}
