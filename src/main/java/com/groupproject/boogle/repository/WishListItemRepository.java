package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishListItem;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, User> {
	
}
