package com.groupproject.boogle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.User;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findAllCardByUser(User user);
	
	Card findCardByCardNumber(String cardNumber);
	
	Card findCardByUserAndDefaultCard(User user, boolean trueOrFalse);
	
}
