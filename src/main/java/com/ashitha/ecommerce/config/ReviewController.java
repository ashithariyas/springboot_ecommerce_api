package com.ashitha.ecommerce.config;

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

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.exception.UserException;
import com.ashitha.ecommerce.model.Rating;
import com.ashitha.ecommerce.model.Review;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.request.RatingRequest;
import com.ashitha.ecommerce.request.ReviewRequest;
import com.ashitha.ecommerce.service.ReviewService;
import com.ashitha.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Review> createRating(@RequestBody ReviewRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {

		User user = userService.findUserProfieByJwt(jwt);
		Review review = reviewService.createReview(req, user);
		return new ResponseEntity<>(review, HttpStatus.CREATED);

	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {

		List<Review> reviews = reviewService.getAllReview(productId);
		return new ResponseEntity<>(reviews, HttpStatus.ACCEPTED);
	}
}
