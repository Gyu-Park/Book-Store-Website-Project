package com.groupproject.boogle.controller;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.boogle.model.Card;
import com.groupproject.boogle.model.CustomUserDetails;
import com.groupproject.boogle.model.Order;
import com.groupproject.boogle.model.OrderItem;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishList;
import com.groupproject.boogle.repository.CardRepository;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.CardService;
import com.groupproject.boogle.service.OrderService;
import com.groupproject.boogle.service.ShoppingCartService;
import com.groupproject.boogle.service.WishListService;

@Controller
public class AccountController {

	@Value("${version}")
	private String version;

	@Autowired
	private WishListService wishListService;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CardService cardService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserRepository userRepository;

	CustomUserDetails customUserDetails;

	@GetMapping("/account")
	public String viewAccountPage(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		model.addAttribute("version", version);

		// for login and security tab
		customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		model.addAttribute("user", user);

		// for order tab
		List<Order> orderList = orderService.findAllOrdersByUser(user);
		model.addAttribute("orderList", orderList);

		// for Payment Option tab
		List<Card> card = cardService.findAllCardByUser(user);
		for (Card card1 : card) {
			if (card1.isDefaultCard()) {
				card.remove(card1);
				card.add(0, card1);
				break;
			}
		}
		model.addAttribute("card", card);

		// for WishList tab
		WishList wishList = wishListService.getWishListByUser(user);
		model.addAttribute("wishList", wishList);

		return "account";
	}

	/* Login and Security Tab Section */

	@PostMapping("/account/changeName")
	public String updateName(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@ModelAttribute("firstName") String firstName,
			@ModelAttribute("lastName") String lastName) {

		customUserDetails.getUser().setFirstname(firstName);
		customUserDetails.getUser().setLastname(lastName);
		userRepository.saveAndFlush(customUserDetails.getUser());

		return "redirect:/account";
	}

	@PostMapping("/account/changeEmail")
	public String updateEmail(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@ModelAttribute("email") String email) {

		customUserDetails.getUser().setEmail(email);
		userRepository.saveAndFlush(customUserDetails.getUser());

		return "redirect:/account";
	}

	@PostMapping("/account/changePhoneNumber")
	public String updatePhone(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@ModelAttribute("phone") String phone) {

		customUserDetails.getUser().getUserInfo().setPhone(phone);
		userRepository.saveAndFlush(customUserDetails.getUser());

		return "redirect:/account";
	}

	@PostMapping("/account/changePassword")
	public String updatePassword(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@ModelAttribute("password") String password) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		customUserDetails.getUser().setPassword(encodedPassword);
		userRepository.saveAndFlush(customUserDetails.getUser());

		return "redirect:/account";
	}

	@PostMapping("/account/changeAddress")
	public String updateAddress(@AuthenticationPrincipal CustomUserDetails customUserDetails,
			@ModelAttribute("address-street") String addressStreet,
			@ModelAttribute("address-apt") String addressApt,
			@ModelAttribute("address-city") String addressCity,
			@ModelAttribute("address-state") String addressState,
			@ModelAttribute("address-zip") String addressZip) {

		customUserDetails.getUser().getUserDetailsTable().setStreet(addressStreet);
		customUserDetails.getUser().getUserDetailsTable().setApt(addressApt);
		customUserDetails.getUser().getUserDetailsTable().setCity(addressCity);
		customUserDetails.getUser().getUserDetailsTable().setState(addressState);
		customUserDetails.getUser().getUserDetailsTable().setZip(addressZip);
		userRepository.saveAndFlush(customUserDetails.getUser());

		return "redirect:/account";
	}
	
	/* Order Tab Section */
	@GetMapping("/account/buyItAgain")
	public String buyItAgain(Long orderId, HttpServletRequest request, Model model) {
		Order order = orderService.findOrderById(orderId);
		List<OrderItem> orderItemList = order.getOrderItemList();
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		HttpSession session = request.getSession(true);
		
		Iterator<OrderItem> iter =  orderItemList.iterator();
		while(iter.hasNext()) {
			OrderItem orderItem = iter.next();
			if(sessionToken == null) {
				sessionToken = UUID.randomUUID().toString();
				session.setAttribute("sessionToken", sessionToken);
				shoppingCartService.addShoppingCart(orderItem.getBook().getIsbn13(), sessionToken, orderItem.getQuantity());
			} else {
				shoppingCartService.addToExistingShoppingCart(orderItem.getBook().getIsbn13(), sessionToken, orderItem.getQuantity());
			}
		}
		
		return "redirect:/cart";
	}
	
	@GetMapping("/account/writeReview/{isbn13}")
	public String buyItAgain(@PathVariable("isbn13") String isbn13, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("fromAccount", true);
		return "redirect:/product?isbn13="+isbn13;
	}
	

	/* Wish List Tab Section */

	@GetMapping("/addToWishList/{isbn13}")
	public String addToWishList(@PathVariable("isbn13") String isbn13) {
		customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		wishListService.addToWishList(isbn13, user);
		return "redirect:/product?isbn13=" + isbn13;
	}

	@GetMapping("/removeWishListItem/{isbn13}")
	public String removeItemFromWishList(@PathVariable("isbn13") String isbn13, RedirectAttributes redirectAttributes) {
		customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		wishListService.removeItemWishList(isbn13, user);

		redirectAttributes.addFlashAttribute("activeTab", 4);

		return "redirect:/account";
	}

	/* Card Tab Section */

	@PostMapping("/account/addCard")
	public String addCard(Card card, RedirectAttributes redirectAttributes) {
		if (card == null || card.getCardCvv() == null || card.getCardExpMonth() == null 
				|| card.getCardExpYear() == null || card.getCardHolderName() == null 
				|| card.getCardNumber() == null || card.getPaymentOptionName() == null) {
			return "redirect:/account";
		}
		customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		card.setUser(user);
		cardRepository.save(card);

		redirectAttributes.addFlashAttribute("activeTab", 3);

		return "redirect:/account";
	}

	@PostMapping("/account/setDefaultCard")
	public String setDefaultCard(Card defaultCard, RedirectAttributes redirectAttributes) {
		customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		List<Card> cardList = cardService.findAllCardByUser(user);
		for (Card card : cardList) {
			if (card.getPaymentOptionId().equals(defaultCard.getPaymentOptionId())) {
				card.setDefaultCard(true);
			} else {
				card.setDefaultCard(false);
			}
		}
		cardRepository.saveAll(cardList);

		redirectAttributes.addFlashAttribute("activeTab", 3);

		return "redirect:/account";
	}
	
	@PostMapping("/account/editCard")
	public String editCard(Card card, Long paymentOptionId, String cardName, String cardNum, Byte cardExpMonth, 
						Byte cardExpYear, String cardCVV, RedirectAttributes redirectAttributes) {
		
		card = cardService.findCardByPaymentOptionId(paymentOptionId);
		card.setCardHolderName(cardName);
		card.setCardNumber(cardNum);
		card.setCardExpMonth(cardExpMonth);
		card.setCardExpYear(cardExpYear);
		card.setCardCvv(cardCVV);
		
		cardRepository.saveAndFlush(card);
		
		redirectAttributes.addFlashAttribute("activeTab", 3);
		
		return "redirect:/account";
	}

	@PostMapping("/account/removeCard")
	public String removeCard(Card card, RedirectAttributes redirectAttributes) {
		customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = customUserDetails.getUser();
		List<Card> cardList = cardService.findAllCardByUser(user);
		Card removeCard = null;
		for (Card card1 : cardList) {
			if (card.getPaymentOptionId().equals(card.getPaymentOptionId())) {
				removeCard = card1;
				break;
			}
		}
		cardRepository.delete(removeCard);

		redirectAttributes.addFlashAttribute("activeTab", 3);

		return "redirect:/account";
	}

}
