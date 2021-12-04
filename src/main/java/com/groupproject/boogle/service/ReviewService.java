package com.groupproject.boogle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.Review;
import com.groupproject.boogle.repository.ReviewRepository;

@Service("ReviewService")
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public List<Review> findAllReviewsByBook(Book book) {
		return reviewRepository.findAllReviewsByBook(book);
	};

}
