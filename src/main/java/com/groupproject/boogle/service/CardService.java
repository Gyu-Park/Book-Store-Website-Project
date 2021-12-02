package com.groupproject.boogle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.CardRepository;

@Service("CardService")
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	public List<Card> findAllCardByUser(User user) { 
		return cardRepository.findAllCardByUser(user);
	}
	
	public Card findCardByCardNumber(String cardNumber) {
		return cardRepository.findCardByCardNumber(cardNumber);
	}
	
	public Card findCardByPaymentOptionId(Long paymentOptionId) {
		return cardRepository.getById(paymentOptionId);
	}
	
	public Card findDefaultCard(User user) {
		return cardRepository.findCardByUserAndDefaultCard(user, true);
	}

}
