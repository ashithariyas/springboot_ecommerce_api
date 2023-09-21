package com.ashitha.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashitha.ecommerce.exception.CartItemException;
import com.ashitha.ecommerce.exception.UserException;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.response.ApiResponse;
import com.ashitha.ecommerce.service.CartItemService;
import com.ashitha.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt) throws UserException,CartItemException{
		
		User user=userService.findUserProfieByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("delete item from cart");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}	
}
