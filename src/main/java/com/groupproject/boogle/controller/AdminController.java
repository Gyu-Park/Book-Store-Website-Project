package com.groupproject.boogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.repository.BookRepository;

@Controller
public class AdminController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/addBook")
	public String viewAddBookPage(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@PostMapping("/process_addBook")
	public String processAddBook(Book book) {
		bookRepository.save(book);
		
		return "addBook";
	}
}
