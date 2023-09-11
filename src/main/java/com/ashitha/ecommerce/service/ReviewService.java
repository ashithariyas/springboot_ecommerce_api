package com.ashitha.ecommerce.service;

import java.util.List;

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.model.Review;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
}
