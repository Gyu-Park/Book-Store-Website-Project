package com.groupproject.boogle.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="shippingAddress")
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String shippingAddressReceiver;
	
	private String shippingAddressStreet;
	
	private String shippingAddressCity;
	
	private String shippingAddressState;
	
	private String shippingAddressZip;
	
	@OneToOne
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShippingAddressReceiver() {
		return shippingAddressReceiver;
	}

	public void setShippingAddressReceiver(String shippingAddressReceiver) {
		this.shippingAddressReceiver = shippingAddressReceiver;
	}

	public String getShippingAddressStreet() {
		return shippingAddressStreet;
	}

	public void setShippingAddressStreet(String shippingAddressStreet) {
		this.shippingAddressStreet = shippingAddressStreet;
	}

	public String getShippingAddressCity() {
		return shippingAddressCity;
	}

	public void setShippingAddressCity(String shippingAddressCity) {
		this.shippingAddressCity = shippingAddressCity;
	}

	public String getShippingAddressState() {
		return shippingAddressState;
	}

	public void setShippingAddressState(String shippingAddressState) {
		this.shippingAddressState = shippingAddressState;
	}

	public String getShippingAddressZip() {
		return shippingAddressZip;
	}

	public void setShippingAddressZip(String shippingAddressZip) {
		this.shippingAddressZip = shippingAddressZip;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(shippingAddressCity, shippingAddressReceiver, shippingAddressState, shippingAddressStreet,
				shippingAddressZip, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingAddress other = (ShippingAddress) obj;
		return Objects.equals(shippingAddressCity, other.shippingAddressCity)
				&& Objects.equals(shippingAddressReceiver, other.shippingAddressReceiver)
				&& Objects.equals(shippingAddressState, other.shippingAddressState)
				&& Objects.equals(shippingAddressStreet, other.shippingAddressStreet)
				&& Objects.equals(shippingAddressZip, other.shippingAddressZip) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", shippingAddressReceiver=" + shippingAddressReceiver
				+ ", shippingAddressStreet=" + shippingAddressStreet + ", shippingAddressCity=" + shippingAddressCity
				+ ", shippingAddressState=" + shippingAddressState + ", shippingAddressZip=" + shippingAddressZip
				+ ", order=" + order + "]";
	}

}
