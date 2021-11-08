package com.groupproject.boogle.service;

import com.groupproject.boogle.model.Book;

public interface IBookService {
	Book findByIsbn13(String isbn13);
}
