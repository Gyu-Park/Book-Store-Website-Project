package com.groupproject.boogle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.boogle.model.Newsletter;
import com.groupproject.boogle.repository.NewsletterRepository;
import com.groupproject.boogle.service.EmailService;
import com.groupproject.boogle.service.NewsletterService;

@Controller
public class NewsletterController {
	
	@Autowired
	NewsletterRepository newsletterRepository;
	
	@Autowired
	NewsletterService newsletterService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/newsletter")
	public String addNewsletter (String email) {
		if (newsletterService.findNewsletterByemail(email) != null) {
			return "redirect:/home";
		} else {
			Newsletter newsletter = new Newsletter();
			newsletter.setEmail(email);
			newsletterRepository.save(newsletter);
			newsletter = newsletterService.findNewsletterByemail(email);
			emailService.constructNewsletterEmail(newsletter);
		}
		return "redirect:/home";
	}

}
