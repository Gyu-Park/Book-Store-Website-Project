package com.groupproject.boogle.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.groupproject.boogle.aesEncryption.AES;

@Entity(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Temporal(TemporalType.DATE)
	private Date shippingDate;
	
	private String orderStatus;
	
	private BigDecimal orderTotal;
	
	private String cardNumber;
	
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItemList;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ShippingAddress shippingAddress;
	
	@OneToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal oderTotal) {
		this.orderTotal = oderTotal;
	}

	public String getCardNumber() {
		return AES.decrypt(this.cardNumber, AES.secretKeyBoogle);
	}
	
	public String getCardNumberWithoutDecryption() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		String encryptedCardNumber = AES.encrypt(cardNumber.replaceAll("\\D", ""), AES.secretKeyBoogle);
		this.cardNumber = encryptedCardNumber;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, guest, orderDate, orderId, orderItemList, orderStatus, orderTotal,
				shippingAddress, shippingDate, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(cardNumber, other.cardNumber) && Objects.equals(guest, other.guest)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderItemList, other.orderItemList) && Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(orderTotal, other.orderTotal)
				&& Objects.equals(shippingAddress, other.shippingAddress)
				&& Objects.equals(shippingDate, other.shippingDate) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", shippingDate=" + shippingDate
				+ ", orderStatus=" + orderStatus + ", orderTotal=" + orderTotal + ", cardNumber=" + cardNumber
				+ ", orderItemList=" + orderItemList + ", user=" + user + ", shippingAddress=" + shippingAddress
				+ ", guest=" + guest + "]";
	}

}
