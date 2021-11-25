package com.groupproject.boogle.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class CartController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping("/cart")
	public String viewCartPage(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("version", version);
		
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
	
	@PostMapping("/cart/updateShoppingCart")
	public String updateCartItem(@RequestParam("item_id") Long id, 
			@RequestParam("quantity") int quantity) {
		
		shoppingCartService.updateShoppingCartItem(id, quantity);
		return "redirect:/cart";
	}
	
	@GetMapping("/cart/removeItem/{id}")
	public String removeItem(@PathVariable("id") Long id, HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessionToken");
		shoppingCartService.removeCartItemFromShoppingCart(id, sessionToken);
		return "redirect:/cart";
	}
}
