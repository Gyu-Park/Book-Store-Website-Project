package com.groupproject.boogle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.CustomUserDetails;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishList;
import com.groupproject.boogle.repository.CardRepository;
import com.groupproject.boogle.service.CardService;
import com.groupproject.boogle.service.WishListService;

@Controller
public class AccountController {
	
	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private CardService cardService;
	
	private CustomUserDetails customUserDetails;

	@GetMapping("/account")
	public String viewAccountPage(Model model) {
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		WishList wishList = wishListService.getWishListByUser(user);
		model.addAttribute("wishList", wishList);
		List<Card> card = cardService.findAllCardByUser(user);
		model.addAttribute("card", card);
		return "account";
	}
	
	@GetMapping("/addToWishList/{isbn13}")
	public String addToWishList(@PathVariable("isbn13") String isbn13) {
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		wishListService.addToWishList(isbn13, user);
		return "redirect:/product?isbn13="+isbn13;
	}
	
	@GetMapping("/removeWishListItem/{isbn13}")
	public String removeItemFromWishList(@PathVariable("isbn13") String isbn13) {
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		wishListService.removeItemWishList(isbn13, user);
		return "redirect:/account";
	}
	
	@PostMapping("/account/addCard")
	public String addCard(Card card) {
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		card.setUser(user);
		cardRepository.save(card);
		return "redirect:/account";
	}
	
}
