package com.example.springKafka.dao;

import com.example.springKafka.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    Order createOrder(Order order);
    Optional<Order> getOrderById(Long ID);
    List<Order> getAllOrders();

}
