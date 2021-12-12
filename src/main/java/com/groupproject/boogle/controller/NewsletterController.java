package com.groupproject.boogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Newsletter;
import com.groupproject.boogle.repository.NewsletterRepository;
import com.groupproject.boogle.service.EmailService;

@Controller
public class NewsletterController {
	
	@Autowired
	NewsletterRepository newsletterRepository;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/newsletter")
	public String addNewsletter (String email) {
		Newsletter newsletter = new Newsletter();
		newsletter.setEmail(email);
		newsletterRepository.save(newsletter);
		emailService.constructNewsletterEmail(newsletter);
		
		return "redirect:/home";
	}

}
