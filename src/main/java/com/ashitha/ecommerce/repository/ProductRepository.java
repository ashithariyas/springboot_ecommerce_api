package com.ashitha.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashitha.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	
	@Query("SELECT p FROM Product p " +
	        "WHERE (:category IS NULL OR p.category.name = :category OR :category = '') " +
	        "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountPrice BETWEEN :minPrice AND :maxPrice)) " +
	        "AND (:minDiscount IS NULL OR p.discountpercent >= :minDiscount) " +
	        "ORDER BY " +
	        "CASE WHEN :sort = 'price_low' THEN p.discountPrice END ASC, " +
	        "CASE WHEN :sort = 'price_high' THEN p.discountPrice END DESC"
	)
	public List<Product> filterProducts(@Param("category") String category,
	                                    @Param("minPrice") Integer minPrice,
	                                    @Param("maxPrice") Integer maxPrice,
	                                    @Param("minDiscount") Integer minDiscount,
	                                    @Param("sort") String sort);
}