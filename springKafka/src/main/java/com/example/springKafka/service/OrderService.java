package com.example.springKafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springKafka.entity.Order;
import com.example.springKafka.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	

	public   Order createOrder(Order order) {
		return orderRepository.save(order);
	}
	
	
	
}
