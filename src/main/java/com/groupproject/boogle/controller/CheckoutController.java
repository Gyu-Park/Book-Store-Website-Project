package com.groupproject.boogle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

	@GetMapping("/checkout")
	public String viewCheckoutPage(Model model) {
		return "checkout";
	}
}
