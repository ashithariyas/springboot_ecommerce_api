package com.ashitha.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashitha.ecommerce.config.JwtProvider;
import com.ashitha.ecommerce.exception.UserException;
import com.ashitha.ecommerce.model.Cart;
import com.ashitha.ecommerce.model.User;
import com.ashitha.ecommerce.repository.UserRepository;
import com.ashitha.ecommerce.request.LoginRequest;
import com.ashitha.ecommerce.response.AuthResponse;
import com.ashitha.ecommerce.service.CartService;
import com.ashitha.ecommerce.service.CustomUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserServiceImpl customUserService;
	
	@Autowired
	private CartService cartService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

		String email = user.getEmail();
		String password = user.getPassword();
		String firstNameString = user.getFirstName();
		String lastNameString = user.getLastName();

		User isEmailExist = userRepository.findByEmail(email);

		if (isEmailExist != null) {
			throw new UserException("Email is Already Used With Another Account");
		}

		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstNameString);
		createdUser.setLastName(lastNameString);

		User savedUser = userRepository.save(createdUser);
		Cart cart=cartService.createCart(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();

		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest logingRequest) {

		String username = logingRequest.getEmail();
		String password = logingRequest.getPassword();

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();

		authResponse.setJwt(token);
		authResponse.setMessage("Signin Success");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

	}

	private Authentication authenticate(String username, String password) {

		UserDetails userDetails = customUserService.loadUserByUsername(username);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username...");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
