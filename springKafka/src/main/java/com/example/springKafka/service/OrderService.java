package com.example.springKafka.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.springKafka.dto.OrderDTO;
import com.example.springKafka.dao.OrderDAO;
import com.example.springKafka.entity.Order;
import com.example.springKafka.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "order_topic";

	public  OrderDTO createOrder(OrderDTO orderdto) {
		Order order = convertToEntity(orderdto);
		Order savedOrder = orderDAO.createOrder(order);
		try{
			kafkaTemplate.send(TOPIC, "Order created: " + savedOrder.getId());
		}catch(Exception e){
			System.out.println("kafka producer error - order created message not sent ");
		}
		return convertToDTO(savedOrder);
	}


	public  boolean  checkKafkaMessageTransferAfterSecurity () {
		try {
			kafkaTemplate.send(TOPIC, "Kafka message from order: ");
			return true;
		} catch (Exception e) {
			System.out.println("kafka producer error - order created message not sent ");
			return false;
		}
	}


	private Order convertToEntity(OrderDTO orderdto) {
		Order order = new Order();
		order.setId(orderdto.getId());
		order.setCustomerName(orderdto.getCustomerName());
		order.setOrders(orderdto.getOrders());
		return order;
	}


	public List<OrderDTO> getAllOrders() {
		// TODO Auto-generated method stub
		List<OrderDTO> allOrders = new ArrayList<OrderDTO>();
		allOrders =orderDAO.getAllOrders().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		return allOrders;
		
	}


	@SuppressWarnings("deprecation")
	public OrderDTO getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderDAO.getOrderById(id)
				.map(this::convertToDTO)
				.orElse(null);
	}
		

    
    private OrderDTO convertToDTO (Order order) {
    	return new OrderDTO (order.getId(),order.getCustomerName(),order.getOrders());
    }
	
	
	
}
