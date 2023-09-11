package com.ashitha.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.model.Rating;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.request.RatingRequest;

@Service
public interface RatingService {
	
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);
	

}
