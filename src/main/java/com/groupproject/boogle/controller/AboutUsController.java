package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class AboutUsController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/aboutUs")
	public String viewAboutUsPage(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		model.addAttribute("version", version);
		
		return "aboutUs";
	}
}
