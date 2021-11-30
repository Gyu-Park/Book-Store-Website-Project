package com.groupproject.boogle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/")
	public String viewDefaultPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		List<Book> bestSellers = bookService.getBestSellers();
		model.addAttribute("bestSellers", bestSellers);
		
		return "home";
	}
	
	@GetMapping("/home")
	public String viewHomePage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		List<Book> bestSellers = bookService.getBestSellers();
		model.addAttribute("bestSellers", bestSellers);
		
		return "home";
	}

}
