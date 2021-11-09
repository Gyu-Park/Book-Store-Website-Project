package com.groupproject.boogle.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.CartItem;
import com.groupproject.boogle.model.ShoppingCart;
import com.groupproject.boogle.repository.CartItemRepository;
import com.groupproject.boogle.repository.ShoppingCartRepository;

@Service("shoppingCartService")
public class ShoppingCartService {
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private BookService bookService;
	
	public ShoppingCart addShoppingCart(String isbn13, String sessionToken, int quantity) {
		ShoppingCart shoppingCart = new ShoppingCart();
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(quantity);
		cartItem.setDate(new Date());
		cartItem.setBook(bookService.findByIsbn13(isbn13));
		shoppingCart.getItems().add(cartItem);
		shoppingCart.setSessionToken(sessionToken);
		shoppingCart.setDate(new Date());
		return shoppingCartRepository.save(shoppingCart);
	}

	public ShoppingCart addToExistingShoppingCart(String isbn13, String sessionToken, int quantity) {
		ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
		Book book = bookService.findByIsbn13(isbn13);
		Boolean productDoesExistInTheCart = false;
		if (shoppingCart != null) {
			Set<CartItem> items = shoppingCart.getItems();
			for (CartItem item : items) {
				if(item.getBook().equals(book)) {
					productDoesExistInTheCart = true;
					item.setQuantity(item.getQuantity() + quantity);
					shoppingCart.setItems(items);
					return shoppingCartRepository.saveAndFlush(shoppingCart);
				}
			}
		}
		if(!productDoesExistInTheCart && (shoppingCart != null)) {
			CartItem cartItem = new CartItem();
			cartItem.setDate(new Date());
			cartItem.setBook(book);
			cartItem.setQuantity(quantity);
			shoppingCart.getItems().add(cartItem);
			return shoppingCartRepository.saveAndFlush(shoppingCart);
		}
		return this.addShoppingCart(isbn13, sessionToken, quantity);
	}

	public ShoppingCart getShoppingCartBySessionToken(String sessionToken) {
		return shoppingCartRepository.findBySessionToken(sessionToken);
	}

	public void updateShoppingCartItem(Long id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id).get();
		cartItem.setQuantity(quantity);
		cartItemRepository.saveAndFlush(cartItem);
	}

}
