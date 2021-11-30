package com.groupproject.boogle.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.Category;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.CategoryService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class ProductController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@GetMapping("/product")
	public String viewProductPage(@PathParam("isbn13") String isbn13, Model model, HttpServletRequest request) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		model.addAttribute("version", version);
		Book book = bookService.findByIsbn13(isbn13);
		model.addAttribute("book", book);
		List<Category> category = book.getCategory();
		List<Book> similarItems = category.get(0).getBook();
		
		// remove the same book in the smilarItems Book List.
		short count = 0;
		for (Iterator<Book> iterator = similarItems.iterator(); iterator.hasNext();) {
			Book similarItem = iterator.next();
			if (similarItem.getIsbn13().equals(book.getIsbn13())) {
				iterator.remove();
				break;
			}
			count++;
			if (count == 5) {
				break;
			}
		}
		
		
		model.addAttribute("similarItems", similarItems);
		return "product";
	}
	
}
