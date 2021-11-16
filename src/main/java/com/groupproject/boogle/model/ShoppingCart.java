package com.groupproject.boogle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Transient
	private Double totalPrice;
	
	@Transient
	private int itemsNumber;
	
	@Transient
	private int totalItemsNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CartItem> items = new HashSet<CartItem>();
	
	private String sessionToken;
	
	public ShoppingCart() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalPrice() {
		Double sum = 0.0;
		for(CartItem item : this.items) {
			sum = sum + item.getBook().getPrice() * item.getQuantity();
		}
		
		return sum;
	}

	public int getItemsNumber() {
		return this.items.size();
	}

	public int getTotalItemsNumber() {
		int numberOfItems = 0;
		for(CartItem item : this.items) {
			numberOfItems =+ item.getQuantity();
		}
		return numberOfItems;
	}

	public Set<CartItem> getItems() {
		return items;
	}

	public void setItems(Set<CartItem> items) {
		this.items = items;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, items, itemsNumber, sessionToken, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(items, other.items)
				&& itemsNumber == other.itemsNumber && Objects.equals(sessionToken, other.sessionToken)
				&& Objects.equals(totalPrice, other.totalPrice);
	}
	
	

}
