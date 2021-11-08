package com.groupproject.boogle.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.service.BookService;

@Controller
public class ProductController {
	
	@Autowired
	BookService bookService;

	@GetMapping("/product")
	public String viewProductPage(@PathParam("isbn13") String isbn13, Model model) {
		
		Book book = bookService.findByIsbn13(isbn13);
		model.addAttribute("book", book);
		return "product";
	}
	
}
