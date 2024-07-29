package com.example.springKafka.impl;

import com.example.springKafka.dao.OrderDAO;
import com.example.springKafka.entity.Order;
import com.example.springKafka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@Repository
public class OrderImpl implements OrderDAO {

    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return  orderRepository.save(order);
    }


    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    @Override
    public Optional<Order> getOrderById(Long Id){
        return orderRepository.findById(Id);
    }
}
