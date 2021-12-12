package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Newsletter;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
	
	public Newsletter findNewsletterByEmail(String email);
	
}
