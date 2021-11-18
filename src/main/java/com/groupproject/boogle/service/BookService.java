package com.groupproject.boogle.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.repository.BookRepository;

@Service
public class BookService implements IBookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAllBook() { return bookRepository.findAll(); }

	@Override
	public Book findByIsbn13(String isbn13) {
		return bookRepository.getById(isbn13);
	}
	
	public List<Book> search(String keyword) {
		return bookRepository.search(keyword);
	}

}
