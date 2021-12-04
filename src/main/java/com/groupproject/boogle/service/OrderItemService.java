package com.groupproject.boogle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.OrderItem;
import com.groupproject.boogle.repository.OrderItemRepository;

@Service("OrderItemService")
public class OrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	public List<OrderItem> findOrderItemsByBook(Book book) {
		return orderItemRepository.findOrderItemsByBook(book);
	}
	
}
