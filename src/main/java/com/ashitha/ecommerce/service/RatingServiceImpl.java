package com.ashitha.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.model.Product;
import com.ashitha.ecommerce.model.Rating;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.repository.RatingRepository;
import com.ashitha.ecommerce.request.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
	
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		return ratingRepository.getAllProductsRating(productId);
	}
}
