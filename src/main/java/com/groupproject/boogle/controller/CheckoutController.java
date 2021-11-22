package com.groupproject.boogle.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.CartItem;
import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.CardRepository;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class CheckoutController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@GetMapping("/checkout")
	public String viewCheckoutPage(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
			return "forward:/cart";
		}
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		model.addAttribute("shoppingCart", shoppingCart);
		Set<CartItem> cartItemList = shoppingCart.getItems();
		
		if(cartItemList.size() == 0) {
			return "forward:/cart";
		}
		
		for (CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getNumber_on_hand() < cartItem.getQuantity()) {
				return "forward:/shoppingCart/cart";
			}
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user;
		if (auth.isAuthenticated()) {
			user = userRepository.findByEmail(auth.getName());
			model.addAttribute("user", user);
			try {
				List<Card> card = cardRepository.findAllCardByUser(user);
				model.addAttribute("userInfo", user.getUserDetailsTable());
				model.addAttribute("card", card.get(0));
			} catch (Exception e) {
				
			}
		}
		
		return "checkout";
	}
}
