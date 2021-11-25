package com.groupproject.boogle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class SearchController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private BookService bookService; 
	
	@GetMapping("/search")
	public String viewSearchResultsPage(@Param("keyword") String keyword, HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		List<Book> searchResult = bookService.search(keyword);
		model.addAttribute("keyword", keyword);
		model.addAttribute("book", searchResult);
		
		return "searchResults";
	}

}
