package com.groupproject.boogle.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Book;
import com.groupproject.boogle.model.User;
import com.groupproject.boogle.model.WishList;
import com.groupproject.boogle.model.WishListItem;
import com.groupproject.boogle.repository.WishListItemRepository;
import com.groupproject.boogle.repository.WishListRepository;

@Service("WishListService")
public class WishListService {
	
	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private WishListItemRepository wishListItemRepository;
	
	@Autowired
	private BookService bookService;
	
	public WishList getWishListByUser(User user) {
		return  wishListRepository.findByUser(user);
	}
	
	public WishList addToWishList(String isbn13, User user) {
		if (wishListRepository.findByUser(user) == null) {
			WishList wishList = new WishList();
			WishListItem items = new WishListItem();
			
			items.setBook(bookService.findByIsbn13(isbn13));
			wishList.getItems().add(items);
			wishList.setUser(user);
			
			return wishListRepository.saveAndFlush(wishList);
		} else {
			WishList wishList = wishListRepository.findByUser(user);
			Book book = bookService.findByIsbn13(isbn13);
			Boolean productDoesExistInTheList = false;
			if(wishList != null) {
				Set<WishListItem> items = wishList.getItems();
				for (WishListItem item : items) {
					if (item.getBook().equals(book)) {
						productDoesExistInTheList = true;
						break;
					}
				}
			}
			if (!productDoesExistInTheList && (wishList != null)) {
				WishListItem item1 = new WishListItem();
				item1.setBook(book);
				wishList.getItems().add(item1);
				return wishListRepository.saveAndFlush(wishList);
			}
			return null;
		}
	}
	
	public WishList removeItemWishList(String isbn13, User user) {
		WishList WishList = wishListRepository.findByUser(user);
		Set<WishListItem> items = WishList.getItems();
		for(WishListItem item : items) {
			if(item.getBook().getIsbn13().equals(isbn13)) {
				items.remove(item);
				wishListItemRepository.delete(item);
				break;
			}
		}
	    WishList.setItems(items);
	    return wishListRepository.save(WishList);
	}
}
