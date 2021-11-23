package com.groupproject.boogle.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "guest")
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long guestId;
	
	@Column(nullable = false, length = 45)
	private String email;
	
	@Column(nullable = false, length = 200)
	private String fullName;
	
	@Column(nullable = false, length = 45)
	private String phone;

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, guestId, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(guestId, other.guestId) && Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", email=" + email + ", fullName=" + fullName + ", phone=" + phone + "]";
	}

}
