package com.groupproject.boogle.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.CartItem;
import com.groupproject.boogle.model.Guest;
import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.OrderItem;
import com.groupproject.boogle.model.ShippingAddress;
import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.OrderItemRepository;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.BookService;
import com.groupproject.boogle.service.CardService;
import com.groupproject.boogle.service.EmailService;
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
	private CardService cardService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/checkout")
	public String viewCheckoutPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		model.addAttribute("shoppingCart", shoppingCart);
		List<CartItem> cartItemList = shoppingCart.getItems();
		
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
		if (!auth.getName().equals("anonymousUser")) {
			user = userRepository.findByEmail(auth.getName());
			model.addAttribute("user", user);
			model.addAttribute("userInfo", user.getUserDetailsTable());
			try {
				Card card = cardService.findDefaultCard(user);
				model.addAttribute("card", card);
			} catch (NullPointerException e) {
				// if there's no card, do nothing.
			}
		}
		
		return "checkout";
	}
	
	@PostMapping("placeAnOrder")
	public String placeAnOrder(HttpServletRequest request, Model model, 
			Card card, ShippingAddress shippingAddress, Guest guest) {
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		if (sessionToken == null) {
			return "forward:/cart";
		}

		Order order = new Order();
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (!auth.getName().equals("anonymousUser")) {
			user = userRepository.findByEmail(auth.getName());
			order.setUser(user);
			Card orderCard = cardService.findCardByCardNumber(card.getCardNumberWithoutDecryption());
			order.setCardNumber(orderCard.getCardNumber());
		} else {
			guest.setFullName(shippingAddress.getShippingAddressReceiver());
			guestService.addGuestintoDatabase(guest);
			order.setGuest(guest);
			// need to think about how to handle guest's card 
		}
		
		order.setOrderTotal(new BigDecimal(shoppingCart.getTotalPrice())); // actually subtotal.
		List<CartItem> items = shoppingCart.getItems();
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem orderItem = new OrderItem();
		try {
			for(CartItem cartItem : items) {
				orderItem.setBook(cartItem.getBook());
				orderItem.setOrder(order);
				orderItem.setQuantity(cartItem.getQuantity());
				cartItem.getBook().setNumber_on_hand(cartItem.getBook().getNumber_on_hand() - cartItem.getQuantity());
				cartItem.getBook().setSales(cartItem.getBook().getSales() + cartItem.getQuantity());
				orderItemRepository.save(orderItem);
				orderItems.add(orderItem);
				orderItem = new OrderItem();
			}
			
			shoppingCartService.removeAllCartItemFromShoppingCart(shoppingCart);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		order.setOrderItemList(orderItems);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderStatus("created");
		order.setShippingAddress(shippingAddress);
		// order.setOrderTotal();
		orderService.createOrder(order);
		
		if (!auth.getName().equals("anonymousUser")) {
			emailService.constructOrderConfirmationEmailForUser(user, order);
		} else {
			emailService.constructOrderConfirmationEmailForGuest(guest, order);
		}
		
		model.addAttribute("shoppingCart", shoppingCart);
		List<Book> bestSellers = bookService.getBestSellers();
		model.addAttribute("bestSellers", bestSellers);
		
		return "home";
	}

	@GetMapping("paypalPayment")
	public String paypalPayment (HttpServletRequest request, Model model) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		Order order = new Order();
		if (!auth.getName().equals("anonymousUser")) {
			user = userRepository.findByEmail(auth.getName());
			order.setUser(user);
		} else {
			// for guest
		}
		
		List<CartItem> items = shoppingCart.getItems();
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem orderItem = new OrderItem();
		try {
			for(CartItem cartItem : items) {
				orderItem.setBook(cartItem.getBook());
				orderItem.setOrder(order);
				orderItem.setQuantity(cartItem.getQuantity());
				cartItem.getBook().setNumber_on_hand(cartItem.getBook().getNumber_on_hand() - cartItem.getQuantity());
				cartItem.getBook().setSales(cartItem.getBook().getSales() + cartItem.getQuantity());
				orderItemRepository.save(orderItem);
				orderItems.add(orderItem);
				orderItem = new OrderItem();
			}
			
			shoppingCartService.removeAllCartItemFromShoppingCart(shoppingCart);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		order.setOrderItemList(orderItems);
		
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(new BigDecimal(shoppingCart.getTotalPrice()));
		order.setOrderStatus("created");
		orderService.createOrder(order);

		shoppingCartService.removeAllCartItemFromShoppingCart(shoppingCart);
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("version", version);

		return "home";
	}
}
