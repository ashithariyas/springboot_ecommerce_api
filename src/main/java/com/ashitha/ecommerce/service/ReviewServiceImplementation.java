package com.ashitha.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.model.Product;
import com.ashitha.ecommerce.model.Review;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.repository.ProductRepository;
import com.ashitha.ecommerce.repository.ReviewRepository;
import com.ashitha.ecommerce.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
        
		Product product=productService.findProductById(req.getProductId());
		
		Review review = new Review();
		
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());	
		review.setCreatedAt(LocalDateTime.now());
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		return reviewRepository.getAllProductsReview(productId);
	}
}
