package com.example.springKafka.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springKafka.dto.OrderDTO;
import com.example.springKafka.entity.Order;
import com.example.springKafka.service.OrderService;



@RestController
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
		System.out.println("Received new order: " + order);
        return "Order received successfully";
		
	}
	@GetMapping("/getAllOrder")
    public String getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            LOGGER.info("No orders found.");
        } else {
            for (OrderDTO order : orders) {
                LOGGER.info(order.toString());
            }
        }
        return "All orders returned successfully";
    }
	
	@GetMapping("/hello")
    public String hello() {
        return "Hello from TestController";
    }
	
	@GetMapping(value = "/ping")
	public String getTestData() {
		return "SUCCESS";
	}
	
	
	@GetMapping("/getOrderById/{Id}")
	public  OrderDTO getOrderById(@PathVariable Long Id) {
		return orderService.getOrderById(Id);
		
	}
}




