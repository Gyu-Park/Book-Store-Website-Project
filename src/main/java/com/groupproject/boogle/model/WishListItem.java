package com.groupproject.boogle.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "wishListItem")
public class WishListItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long wishListItemId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "isbn13", nullable = false, updatable = false)
	private Book book;

	public Long getWishListItemId() {
		return wishListItemId;
	}

	public void setWishListItemId(Long wishListItemId) {
		this.wishListItemId = wishListItemId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, wishListItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishListItem other = (WishListItem) obj;
		return Objects.equals(book, other.book) && Objects.equals(wishListItemId, other.wishListItemId);
	}

	@Override
	public String toString() {
		return "WishListItem [wishListItemId=" + wishListItemId + ", book=" + book + "]";
	}

}
