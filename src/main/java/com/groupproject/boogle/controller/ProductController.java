package com.groupproject.boogle.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.Category;
import com.groupproject.boogle.model.OrderItem;
import com.groupproject.boogle.model.Review;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.ReviewRepository;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.CategoryService;
import com.groupproject.boogle.service.OrderItemService;
import com.groupproject.boogle.service.ReviewService;
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
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	OrderItemService orderItemService;

	@GetMapping("/product")
	public String viewProductPage(@PathParam("isbn13") String isbn13, Model model, HttpServletRequest request) {
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		model.addAttribute("version", version);
		
		Book book = bookService.findByIsbn13(isbn13);
		model.addAttribute("book", book);
		
		// for verified purchase
		List<Review> reviews = reviewService.findAllReviewsByBook(book);
		model.addAttribute("reviews", reviews);
		List<OrderItem> orderItems = orderItemService.findOrderItemsByBook(book);
		if(!orderItems.isEmpty()) {
			Iterator<OrderItem> iter = orderItems.iterator();
			List<Long> listOfUsersWhoOrderedThisItem = new ArrayList<Long>();
			while(iter.hasNext()) {
				OrderItem orderItem = iter.next();
				if (orderItem.getOrder().getUser() != null) {
					listOfUsersWhoOrderedThisItem.add(orderItem.getOrder().getUser().getUserid());
				}
			}
			if(listOfUsersWhoOrderedThisItem != null) {
				model.addAttribute("listOfUsersWhoOrderedThisItem", listOfUsersWhoOrderedThisItem);
			}
		}
		
		// for average rating
		if(!reviews.isEmpty()) {
			Iterator<Review> iterator = reviews.iterator();
			double sum = 0;
			while(iterator.hasNext()) {
				sum += iterator.next().getRating();
			}
			BigDecimal avgRating = new BigDecimal(sum / reviews.size()).setScale(1, RoundingMode.HALF_UP);
			model.addAttribute("avgRating", avgRating);
		} else {
			model.addAttribute("avgRating", null);
		}
		
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		if (user != null) {
			model.addAttribute("user", user);
		}
		
		
		return "product";
	}
	
	@PostMapping("/product/createReview")
	public String createReview(HttpServletRequest request, Model model, @PathParam("isbn13") String isbn13, 
								@ModelAttribute("book") Book book, @ModelAttribute("reviewTitle") String reviewTitle,
								@ModelAttribute("reviewDes") String reviewDes, @ModelAttribute("rating") byte rating) {
		
		if(reviewTitle.equals("")) {
			return "redirect:/product?isbn13="+book.getIsbn13();
		} else if(reviewTitle.equals("") && reviewDes.equals("")) {
			return "redirect:/product?isbn13="+book.getIsbn13();
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		
		Review review = new Review();
		review.setBook(book);
		review.setRating(rating);
		review.setReviewDes(reviewDes);
		review.setReviewTitle(reviewTitle);
		review.setUser(user);
		
		reviewRepository.save(review);
		
		return "redirect:/product?isbn13="+book.getIsbn13();
	}
	
	@PostMapping("/product/edtReview")
	public String editReview(HttpServletRequest request, Model model, @PathParam("isbn13") String isbn13, 
								@ModelAttribute("reviewId") Long reviewId,
								@ModelAttribute("book") Book book, @ModelAttribute("reviewTitle") String reviewTitle,
								@ModelAttribute("reviewDes") String reviewDes, @ModelAttribute("rating") byte rating) {
		
		if(reviewTitle.equals("")) {
			return "redirect:/product?isbn13="+book.getIsbn13();
		} else if(reviewTitle.equals("") && reviewDes.equals("")) {
			return "redirect:/product?isbn13="+book.getIsbn13();
		} else if(rating < 0 && rating > 5) {
			return "redirect:/product?isbn13="+book.getIsbn13();
		}
		
		Review review = reviewRepository.getById(reviewId);
		review.setBook(book);
		review.setRating(rating);
		review.setReviewDes(reviewDes);
		review.setReviewTitle(reviewTitle);
		
		reviewRepository.saveAndFlush(review);
		
		return "redirect:/product?isbn13="+book.getIsbn13();
	}
	
}
