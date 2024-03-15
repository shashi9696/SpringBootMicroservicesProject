package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.OrderRequest;
import com.example.service.OrderService;
import com.example.utility.Http;
import com.example.utility.MyResponse;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	// Place New Order
	@PostMapping
	public MyResponse<?> placeOrder(@RequestBody OrderRequest orderRequest) {
		OrderRequest placeRequest = orderService.placeOrder(orderRequest);
		return MyResponse.builder().status(Http.SUCCESS).statusCode(Http.OK).data(placeRequest).build();
	}

}
