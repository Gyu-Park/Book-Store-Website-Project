package com.groupproject.boogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.service.NewsletterService;

@Controller
public class NewsletterController {
	
	@Autowired
	NewsletterService newsletterService;
	
	@PostMapping("/newsletter")
	public String addNewsletter (String email) {
		newsletterService.addEmailforNewsLetterintoDatabase(email);
		
		return "redirect:/home";
	}

}
