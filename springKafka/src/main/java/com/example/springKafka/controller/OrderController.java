package com.example.springKafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springKafka.entity.Order;
import com.example.springKafka.service.OrderService;



@RestController
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
		System.out.println("Received new order: " + order);
        return "Order received successfully";
		
	}

	
	@GetMapping("/hello")
    public String hello() {
        return "Hello from TestController";
    }
	
	@GetMapping(value = "/ping")
	public String getTestData() {
		return "SUCCESS";
	}
}



