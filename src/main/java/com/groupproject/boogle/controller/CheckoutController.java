package com.groupproject.boogle.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.CartItem;
import com.groupproject.boogle.model.Guest;
import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.OrderItem;
import com.groupproject.boogle.model.ShippingAddress;
import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.CardRepository;
import com.groupproject.boogle.repository.OrderItemRepository;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.CardService;
import com.groupproject.boogle.service.GuestService;
import com.groupproject.boogle.service.OrderService;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class CheckoutController {
	
	@Value("${version}")
	private String version;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@GetMapping("/checkout")
	public String viewCheckoutPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
			return "forward:/cart";
		}
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		model.addAttribute("shoppingCart", shoppingCart);
		Set<CartItem> cartItemList = shoppingCart.getItems();
		
		if(cartItemList.size() == 0) {
			return "forward:/cart";
		}
		
		for (CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getNumber_on_hand() < cartItem.getQuantity()) {
				return "forward:/shoppingCart/cart";
			}
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user;
		if (auth.isAuthenticated()) {
			user = userRepository.findByEmail(auth.getName());
			model.addAttribute("user", user);
			try {
				List<Card> card = cardRepository.findAllCardByUser(user);
				model.addAttribute("userInfo", user.getUserDetailsTable());
				model.addAttribute("card", card.get(0));
			} catch (Exception e) {
				
			}
		}
		
		return "checkout";
	}
	
	@PostMapping("placeAnOrder")
	public String placeAnOrder(HttpServletRequest request, HttpSession session, Model model, 
			Card card, ShippingAddress shippingAddress, Guest guest) {
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			return "forward:/cart";
		}

		Order order = new Order();
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user;
		if (!auth.getName().equals("anonymousUser")) {
			user = userRepository.findByEmail(auth.getName());
			order.setUser(user);
			try {
				order.setCard(cardService.findAllCardByUser(user).get(0));
			} catch (Exception e) {
				//do nothing
			}
			// think about how to handle guest's card 
		} else {
			guest.setFullName(shippingAddress.getShippingAddressReceiver());
			guestService.addGuestintoDatabase(guest);
			order.setGuest(guest);
		}
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderStatus("created");
		order.setShippingAddress(shippingAddress);
		// order.setOrderTotal();
		orderService.createOrder(order);
		
		Set<CartItem> items = shoppingCart.getItems();
		OrderItem orderItem = new OrderItem();
		for(CartItem cartItem : items) {
			orderItem.setBook(cartItem.getBook());
			orderItem.setOrder(order);
			orderItem.setQuantity(cartItem.getQuantity());
			cartItem.getBook().setNumber_on_hand(cartItem.getBook().getNumber_on_hand() - cartItem.getQuantity());
			orderItemRepository.save(orderItem);
		}
		
		shoppingCartService.removeAllCartItemFromShoppingCart(sessionToken);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "home";
	}
	
}
