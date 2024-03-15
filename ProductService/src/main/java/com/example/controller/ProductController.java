package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProductNotFoundException;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;
import com.example.service.ProductService;
import com.example.utility.Http;
import com.example.utility.MyResponse;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// Create New product
	@PostMapping
	public MyResponse<?> createProduct(@RequestBody ProductRequest productRequest) {
		ProductResponse productResponse = productService.createProduct(productRequest);
		return MyResponse.builder().status(Http.SUCCESS).statusCode(Http.OK).data(productResponse).build();
	}
	
	// Find All Products
	@GetMapping
	public MyResponse<?> getAllProducts() {
		List<ProductResponse>  productResponse = productService.getAllProducts();
		return MyResponse.builder().status(Http.SUCCESS).statusCode(Http.OK).data(productResponse).build();
	}
	
	// Find Single Product
	@GetMapping("/{id}")
	public MyResponse<?> findSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
		ProductResponse productResponse = productService.findSingleProduct(productId);
		return MyResponse.builder().status(Http.SUCCESS).statusCode(Http.OK).data(productResponse).build();
	}
	
	// Update Product
	@PutMapping("/{id}")
	public MyResponse<?> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequest productRequest) throws ProductNotFoundException {
		ProductResponse productResponse = productService.updateProduct(productId, productRequest);
		return MyResponse.builder().status(Http.SUCCESS).statusCode(Http.OK).data(productResponse).build();
	}
	
	// Delete Product
	@DeleteMapping("/{id}")
	public MyResponse<?> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
		String product = productService.deleteProduct(productId);
		return MyResponse.builder().status(Http.SUCCESS).statusCode(Http.OK).data(product).build();
	}
	
	// Update Quantity When Place the Order
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> updateQuantity(@PathVariable("id") Long productId, @RequestParam  int quantity) throws ProductNotFoundException {
		productService.reduceQuantity(productId, quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
