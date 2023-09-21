package com.ashitha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ashitha.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query ("SELECT u From User u Where u.email=:email")
	public User findByEmail(@Param("email")String email);	
}
