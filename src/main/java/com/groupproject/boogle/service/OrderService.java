package com.groupproject.boogle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.OrderRepository;

@Service("OrderService")
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public List<Order> findAllOrdersByUser(User user) {
		return orderRepository.findAllByUser(user);
	}

}
