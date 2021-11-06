package com.groupproject.boogle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String viewDefaultPage() {
		return "home";
	}
	
	@GetMapping("/home")
	public String viewHomePage() {
		return "home";
	}

}
