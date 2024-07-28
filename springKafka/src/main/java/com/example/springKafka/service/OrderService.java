package com.example.springKafka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springKafka.dto.OrderDTO;
import com.example.springKafka.entity.Order;
import com.example.springKafka.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	

	public   Order createOrder(Order order) {
		return orderRepository.save(order);
	}


	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		List<Order> allOrders = new ArrayList<Order>();
		allOrders =orderRepository.findAll();
		return allOrders;
		
	}


	@SuppressWarnings("deprecation")
	public List<String> getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.getById(id).getOrders();
	}
	

    
    private OrderDTO convertToDTO (Order order) {
    	return new OrderDTO (order.getId(),order.getCustomerName(),order.getOrders());
    }
	
	
	
}
