package com.groupproject.boogle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.UserRepository;
import com.groupproject.boogle.service.EmailService;
import com.groupproject.boogle.service.ShoppingCartService;

import net.bytebuddy.utility.RandomString;

@Controller
public class LoginAndRegistrationController {
	
	@Value("${version}")
	private String version;
	
	// @Autowired annotation allows us to instantiate a class without directly creating an object in other classes
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private EmailService emailService;
	
	// @GetMapping is to map HTTP GET requests.
	@GetMapping("/login")
	public String viewLoginPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		return "login";
	}
	
	@GetMapping("/forgotPassword")
	public String viewPassResetPage(HttpServletRequest request, Model model) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		return "passReset";
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
	
	@PostMapping("/forgot_password_1")
	public String forgotPassword1(HttpServletRequest request, Model model, String email, RedirectAttributes redirectAttributes) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		User user = userRepo.findByEmail(email);
		
		if (user != null) {
			String token = RandomString.make(6);
			user.setResetPasswordToken(token);
			userRepo.save(user);
			emailService.constructForgotPasswordEmail(email, token);
			model.addAttribute("email", email);
			model.addAttribute("verified", 2);
			return "passReset";
		} else {
			model.addAttribute("error", "There's no user for this email");
			return "passReset";
		}
	}
	
	@PostMapping("/forgot_password_2")
	public String forgotPassword2(HttpServletRequest request, Model model, String token, String email, RedirectAttributes redirectAttributes) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		User user = userRepo.findByEmail(email);
		
		if (email != null && user.getResetPasswordToken().equals(token)) {
			model.addAttribute("verified", 3);
			model.addAttribute("email", email);
			return "passReset";
		} else {
			model.addAttribute("error", "Verification code is not correct.");
			model.addAttribute("email", email);
			model.addAttribute("verified", 2);
			return "passReset";
		}
	}
	
	@PostMapping("/forgot_password_3")
	public String forgotPassword3(HttpServletRequest request, Model model, String email, String password, RedirectAttributes redirectAttributes) {
		model.addAttribute("version", version);
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
		
		User user = userRepo.findByEmail(email);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "login";
	}

}
