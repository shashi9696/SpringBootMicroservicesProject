package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.exception.ProductNotFoundException;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	// Create New Product
	@Override
	public ProductResponse createProduct(ProductRequest request) {
		Product product = Product.builder()
				.productName(request.getProductName())
				.price(request.getPrice())
				.quantity(request.getQuantity())
				.build();
		product = productRepository.save(product);
		
		ProductResponse productResponse = ProductResponse.builder()
				.productName(product.getProductName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.build();
		return productResponse;
	}
	
	// Find All Products
	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = Optional.ofNullable(productRepository.findAll())
				.map(List::stream)
				.orElseGet(Stream::empty)
				.collect(Collectors.toList());
		
		List<ProductResponse> productResponse = products.stream()
				.map(o -> {
					ProductResponse p = new ProductResponse();
					p.setProductName(o.getProductName());
					p.setPrice(o.getPrice());
					p.setQuantity(o.getQuantity());
					return p;
				})
				.collect(Collectors.toList());
		
		return productResponse;	
	}

	// Find Single Product
	@Override
	public ProductResponse findSingleProduct(Long productId) throws ProductNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with id : "+productId));
		
		ProductResponse productResponse = ProductResponse.builder()
				.productName(product.getProductName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.build();
				
		return productResponse;
	}

	// Update Product
	@Override
	public ProductResponse updateProduct(Long productId, ProductRequest productRequest) throws ProductNotFoundException {
		Product productRes = null;
		boolean isProduct = productRepository.existsById(productId);
		
		if(isProduct) {
			Product product = Product.builder()
					.productId(productId)
					.productName(productRequest.getProductName())
					.price(productRequest.getPrice())
					.quantity(productRequest.getQuantity())
					.build();
			productRes = productRepository.save(product);
		} else {
			throw new ProductNotFoundException("Product not found with id : "+ productId);
		}
		
		ProductResponse productResponse = ProductResponse.builder()
				.productName(productRes.getProductName())
				.price(productRes.getPrice())
				.quantity(productRes.getQuantity())
				.build();
		
		return productResponse;
	}

	// Delete Product
	@Override
	public String deleteProduct(Long productId) throws ProductNotFoundException {
		boolean isProduct = productRepository.existsById(productId);
		if(isProduct) {
			productRepository.deleteById(productId);
		} else {
			throw new ProductNotFoundException("Product not found with id : "+ productId);
		}
		return "Product is deleted with id :"+ productId;
	}

	// Update Quantity When Place the Order
	@Override
	public void reduceQuantity(Long productId, int quantity) throws ProductNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with id : "+productId));
		if(product instanceof Product) {
			if(product.getQuantity() < quantity) {
				throw new ProductNotFoundException("Not having enough quantity of products : "+productId);
			}
			product.setQuantity(product.getQuantity() - quantity);
			productRepository.save(product);
		}
		
	}

}
