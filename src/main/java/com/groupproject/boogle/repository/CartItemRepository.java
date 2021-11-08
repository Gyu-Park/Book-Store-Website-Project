package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
}
