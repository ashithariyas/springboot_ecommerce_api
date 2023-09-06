package com.ashitha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashitha.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	public User findByEmail(String email);
	
}
