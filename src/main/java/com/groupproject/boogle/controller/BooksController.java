package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class BooksController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/books")
	public String viewBooksPage(HttpServletRequest request, Model model) {
		model.addAttribute("book", bookService.getAllBook());
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
		} else {
			ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
			model.addAttribute("shoppingCart", shoppingCart);
		}
		return "books";
	}

}
