package com.ashitha.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.CartItemException;
import com.ashitha.ecommerce.exception.UserException;
import com.ashitha.ecommerce.model.Cart;
import com.ashitha.ecommerce.model.CartItem;
import com.ashitha.ecommerce.model.Product;

@Service
public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);

	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
