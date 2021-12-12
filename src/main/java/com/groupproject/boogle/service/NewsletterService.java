package com.groupproject.boogle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Newsletter;
import com.groupproject.boogle.repository.NewsletterRepository;

@Service("NewsletterService")
public class NewsletterService {
	
	@Autowired
	NewsletterRepository newsletterRepository;
	
	public Newsletter findNewsletterByemail(String email) {
		return newsletterRepository.findNewsletterByEmail(email);
	}

}
