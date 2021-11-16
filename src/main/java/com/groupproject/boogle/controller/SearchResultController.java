package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class SearchResultController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/searchResults")
	public String viewSearchResultsPage(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
		} else {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
			model.addAttribute("shoppingCart", shoppingCart);
		}
		
		return "searchResults";
	}

}
