package com.groupproject.boogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.service.BookService;

@Controller
public class BooksController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public String viewBooksPage(Model model) {
		model.addAttribute("book", bookService.getAllBook());
		return "books";
	}

}
