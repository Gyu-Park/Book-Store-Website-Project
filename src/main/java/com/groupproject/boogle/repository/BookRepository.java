package com.groupproject.boogle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	@Query(value = "SELECT * FROM books WHERE MATCH(title, alias) AGAINST(?1)", 
			nativeQuery = true)
	public List<Book> search(String keyword);

}