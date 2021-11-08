package com.groupproject.boogle.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class CartController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping("/cart")
	public String viewCartPage(Model model) {
		return "cart";
	}
	
	
	@PostMapping("/cart/addToCart")
	public String addToCart(HttpSession session,
							Model model,
							@ModelAttribute("book") Book book,
							@ModelAttribute("qty") int qty) {
		
		String sessionToken = (String) session.getAttribute("sessionToken");
		if(sessionToken == null) {
			sessionToken = UUID.randomUUID().toString();
			session.setAttribute("sessionToken", sessionToken);
			shoppingCartService.addShoppingCart(book.getIsbn13(), sessionToken, qty);
		} else {
			shoppingCartService.addToExistingShoppingCart(book.getIsbn13(), sessionToken, qty);
		}
		
		return "redirect:/product?isbn13="+book.getIsbn13();
		
	}
}
