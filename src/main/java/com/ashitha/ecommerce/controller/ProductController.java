package com.ashitha.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashitha.ecommerce.exception.ProductException;
import com.ashitha.ecommerce.model.Product;
import com.ashitha.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products")
	public ResponseEntity<Page<Product>> findProductBycategoryHandler(@RequestParam(required = false) String category,@RequestParam (required = false)List<String> color,@RequestParam (required = false)List<String> size,@RequestParam (required = false)Integer minPrice,@RequestParam (required = false)Integer maxPrice,
			@RequestParam(required = false) Integer minDiscount,@RequestParam (required = false)String sort,@RequestParam (required = false)String stock,@RequestParam (defaultValue = "0")Integer pageNumber,@RequestParam (defaultValue = "10")Integer pageSize) {
		
		Page<Product> res=productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
		        System.out.println("complete products");
				return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	
}
	
	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product>findProductByIdHandler(@PathVariable Long productId)throws ProductException{	
		Product product=productService.findProductById(productId);
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		
	}
}
