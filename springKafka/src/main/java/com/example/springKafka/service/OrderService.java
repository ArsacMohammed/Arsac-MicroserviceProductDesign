package com.example.springKafka.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


	public List<OrderDTO> getAllOrders() {
		// TODO Auto-generated method stub
		List<OrderDTO> allOrders = new ArrayList<OrderDTO>();
		allOrders =orderRepository.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		return allOrders;
		
	}


	@SuppressWarnings("deprecation")
	public OrderDTO getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id)
				.map(this::convertToDTO)
				.orElse(null);
	}
	

    
    private OrderDTO convertToDTO (Order order) {
    	return new OrderDTO (order.getId(),order.getCustomerName(),order.getOrders());
    }
	
	
	
}
