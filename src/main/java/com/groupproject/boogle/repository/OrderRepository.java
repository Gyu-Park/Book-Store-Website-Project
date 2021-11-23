package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
