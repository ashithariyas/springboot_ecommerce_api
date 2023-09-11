package com.ashitha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashitha.ecommerce.model.Cart;
import com.ashitha.ecommerce.model.CartItem;
import com.ashitha.ecommerce.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	@Query("SELECT ci from CartItem ci Where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userId")
	public CartItem isCartItemExists(@Param("cart")Cart cart,@Param("product")Product product,@Param("size")String size,
			                          @Param("userId")Long userId) ;
}
