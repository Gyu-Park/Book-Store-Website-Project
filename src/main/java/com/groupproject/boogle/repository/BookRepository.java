package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	

}