package com.example.service;

import java.util.List;

import com.example.exception.ProductNotFoundException;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;

public interface ProductService {

	// Create New Product
	ProductResponse createProduct(ProductRequest productRequest);

	// Find All Products
	List<ProductResponse> getAllProducts();

	// Find Single Product
	ProductResponse findSingleProduct(Long productId) throws ProductNotFoundException;

	// Update Product
	ProductResponse updateProduct(Long productId, ProductRequest productRequest) throws ProductNotFoundException;

	// Delete Product
	String deleteProduct(Long productId) throws ProductNotFoundException;

	// Update Quantity When Place the Order
	void reduceQuantity(Long productId, int quantity) throws ProductNotFoundException;

}
