package com.groupproject.boogle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	public List<Review> findAllReviewsByBook(Book book);

}
