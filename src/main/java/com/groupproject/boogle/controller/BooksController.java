package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.CategoryService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class BooksController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/books")
	public String viewBooksPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		model.addAttribute("book", bookService.getAllBook());
		model.addAttribute("category", categoryService.findAll());
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
