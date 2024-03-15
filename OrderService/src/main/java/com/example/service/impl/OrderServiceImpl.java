package com.example.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Order;
import com.example.enums.OrderStatus;
import com.example.model.OrderRequest;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import com.example.service.client.ProductService;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public OrderRequest placeOrder(OrderRequest orderRequest) {
		log.info("Order placing initiated...");
		productService.updateQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("after checking product availablity for product "+ orderRequest.getProductId());
		Order order = Order.builder()
				.productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity())
				.price(orderRequest.getPrice())
				.orderDate(Instant.now())
				.orderStatus(OrderStatus.CREATED)
				.build();
		log.info("Order placed...");
		order = orderRepository.save(order);
		log.info("Product saved successfully after placing order");
		OrderRequest placeRequest = OrderRequest.builder()
				.productId(order.getProductId())
				.quantity(order.getQuantity())
				.price(order.getPrice())
				.build();
				
		return placeRequest;
	}

}
