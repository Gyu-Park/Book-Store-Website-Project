package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/")
	public String viewDefaultPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
		} else {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
			model.addAttribute("shoppingCart", shoppingCart);
		}
		
		return "home";
	}
	
	@GetMapping("/home")
	public String viewHomePage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
		} else {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
			model.addAttribute("shoppingCart", shoppingCart);
		}
		
		return "home";
	}

}
