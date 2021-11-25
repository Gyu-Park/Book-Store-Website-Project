package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.ShoppingCartService;

@Controller
public class LoginAndRegistrationController {
	
	@Value("${version}")
	private String version;
	
	// @Autowired annotation allows us to instantiate a class without directly creating an object in other classes
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	// @GetMapping is to map HTTP GET requests.
	@GetMapping("/login")
	public String viewLoginPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		return "login";
	}
	
	// @PostMapping handles POST type of request method. 
	// this annotation grabs "/process_register" request from the register form in login.html
	@PostMapping("/process_register")
	public String processRegistration(HttpServletRequest request, Model model, User user) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		return "login";
	}

}
