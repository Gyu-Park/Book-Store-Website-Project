package com.groupproject.boogle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByUser(User user);
}
