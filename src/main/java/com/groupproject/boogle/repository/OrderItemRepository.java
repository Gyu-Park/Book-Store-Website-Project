package com.groupproject.boogle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	List<OrderItem>findOrderItemsByBook(Book book);

}
