package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class ProductController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping("/product")
	public String viewProductPage(@PathParam("isbn13") String isbn13, Model model, HttpServletRequest request) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		model.addAttribute("version", version);
		Book book = bookService.findByIsbn13(isbn13);
		model.addAttribute("book", book);
		return "product";
	}
	
}
