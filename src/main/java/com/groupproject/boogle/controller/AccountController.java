package com.groupproject.boogle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.CustomUserDetails;
import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishList;
import com.groupproject.boogle.repository.CardRepository;
import com.groupproject.boogle.service.CardService;
import com.groupproject.boogle.service.OrderService;
import com.groupproject.boogle.service.ShoppingCartService;
import com.groupproject.boogle.service.WishListService;

@Controller
public class AccountController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;
	
	CustomUserDetails customUserDetails;

	@GetMapping("/account")
	public String viewAccountPage(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		model.addAttribute("version", version);
		
		// for login and security tab
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		
		// for order tab
		List<Order> orderList = orderService.findAllOrdersByUser(user);
		model.addAttribute("orderList", orderList);
		
		// for Payment Option tab
		List<Card> card = cardService.findAllCardByUser(user);
		for (Card card1 : card) {
			if(card1.isDefaultCard()) {
				card.remove(card1);
				card.add(0, card1);
				break;
			}
		}
		model.addAttribute("card", card);
		
		// for WishList tab
		WishList wishList = wishListService.getWishListByUser(user);
		model.addAttribute("wishList", wishList);
		
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
	
	@PostMapping("/account/setDefaultCard")
	public String setDefaultCard(Card defaultCard) {
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		List<Card> cardList = cardService.findAllCardByUser(user);
		for (Card card : cardList) {
			if (card.getPaymentOptionId().equals(defaultCard.getPaymentOptionId())) {
				card.setDefaultCard(true);
			} else {
				card.setDefaultCard(false);
			}
		}
		cardRepository.saveAll(cardList);
		return "redirect:/account";
	}
	
	@PostMapping("/account/removeCard")
	public String removeCard(Card defaultCard) {
		customUserDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		List<Card> cardList = cardService.findAllCardByUser(user);
		Card removeCard = null;
		System.out.println("----------------------------------------" + cardList.size());
		for (Card card : cardList) {
			if (card.getPaymentOptionId().equals(defaultCard.getPaymentOptionId())) {
				removeCard = card;
				System.out.println("------------------------in loop if");
				break;
			}
		}
		cardRepository.delete(removeCard);
		return "redirect:/account";
	}
	
}
