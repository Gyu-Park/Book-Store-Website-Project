package com.groupproject.boogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.User;
import com.groupproject.boogle.repository.UserRepository;

@Controller
public class LoginAndRegistrationController {
	
	// @Autowired annotation allows us to instantiate a class without directly creating an object in other classes
	@Autowired
	private UserRepository userRepo;
	
	// @GetMapping is to map HTTP GET requests.
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		return "login";
	}
	
	// @PostMapping handles POST type of request method. 
	// this annotation grabs "/process_register" request from the register form in login.html
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		return "login";
	}

}
