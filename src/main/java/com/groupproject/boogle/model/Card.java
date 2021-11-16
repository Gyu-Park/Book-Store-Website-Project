package com.groupproject.boogle.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="payment_option")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentOptionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId", nullable = false, updatable = false)
	private User user;
	
	@Column(length = 20)
	private String paymentOptionName;
	
	@Column(length = 50)
	private String cardHolderName;
	
	@Column(length = 20)
	private String cardNumber;
	
	private Byte cardExpMonth;
	
	private Byte cardExpYear;
	
	private Short cardCvv;

	public Long getPaymentOptionId() {
		return paymentOptionId;
	}

	public void setPaymentOptionId(Long paymentOptionId) {
		this.paymentOptionId = paymentOptionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPaymentOptionName() {
		return paymentOptionName;
	}

	public void setPaymentOptionName(String paymentOptionName) {
		this.paymentOptionName = paymentOptionName;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Byte getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(Byte cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public Byte getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(Byte cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public Short getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(Short cardCvv) {
		this.cardCvv = cardCvv;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, paymentOptionId, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(cardNumber, other.cardNumber) && Objects.equals(paymentOptionId, other.paymentOptionId)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Card [paymentOptionId=" + paymentOptionId + ", user=" + user + ", paymentOptionName="
				+ paymentOptionName + ", cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber
				+ ", cardExpMonth=" + cardExpMonth + ", cardExpYear=" + cardExpYear + ", cardCvv=" + cardCvv + "]";
	}
	
}
