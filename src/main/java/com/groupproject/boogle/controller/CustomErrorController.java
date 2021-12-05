package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class CustomErrorController implements ErrorController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		model.addAttribute("version", version);
		
		return "error";
	}

}
