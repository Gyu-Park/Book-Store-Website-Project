package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, User> {
	
	WishList findByUser(User user);

}
