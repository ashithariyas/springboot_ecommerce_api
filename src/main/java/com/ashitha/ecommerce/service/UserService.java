package com.ashitha.ecommerce.service;

import com.ashitha.ecommerce.exception.UserException;
import com.ashitha.ecommerce.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfieByJwt(String jwt) throws UserException;
	
	
}
