package com.groupproject.boogle.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishList;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.ShoppingCartService;
import com.groupproject.boogle.service.WishListService;

@Controller
public class CartController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	WishListService wishlistService;

	@GetMapping("/cart")
	public String viewCartPage(HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("version", version);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user;
		if (!auth.getName().equals("anonymousUser")) {
			user = userRepository.findByEmail(auth.getName());
			WishList wishlist = wishlistService.getWishListByUser(user);
			try {
				model.addAttribute("wishlist", wishlist.getItems());
			} catch (NullPointerException e) {
				// do nothing
			}
		}
		
		return "cart";
	}
	
	
	@PostMapping("/cart/addToCart")
	public String addToCart(HttpSession session,
							Model model,
							@RequestParam String action,
							@ModelAttribute("book") Book book,
							@ModelAttribute("qty") int qty,
							@ModelAttribute("sentFromAccountWishList") boolean sentFromAccountWishList,
							RedirectAttributes redirectAttributes) {
		
		if(qty <= 0) {
			return "redirect:/product?isbn13="+book.getIsbn13();
		}
		
		String sessionToken = (String) session.getAttribute("sessionToken");
		if(sessionToken == null) {
			sessionToken = UUID.randomUUID().toString();
			session.setAttribute("sessionToken", sessionToken);
			shoppingCartService.addShoppingCart(book.getIsbn13(), sessionToken, qty);
		} else {
			shoppingCartService.addToExistingShoppingCart(book.getIsbn13(), sessionToken, qty);
		}
		
		if(action.equals("buyNow")) {
			return "redirect:/checkout";
		}
		
		if(sentFromAccountWishList) {
			redirectAttributes.addFlashAttribute("activeTab", 4);
			return "redirect:/account";
		}
		
		return "redirect:/product?isbn13="+book.getIsbn13();
	}
	
	@RequestMapping(value = "/cart/updateShoppingCart", method = RequestMethod.POST, params = "qty")
	public String updateCartItem(@RequestParam("item_id") Long id, 
			@RequestParam("quantity") int quantity) {
		
		shoppingCartService.updateShoppingCartItem(id, quantity);
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/cart/updateShoppingCart", method = RequestMethod.POST, params = "remove")
	public String removeItem(@RequestParam("item_id") Long id, HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessionToken");
		shoppingCartService.removeCartItemFromShoppingCart(id, sessionToken);
		return "redirect:/cart";
	}
}
