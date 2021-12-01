package com.groupproject.boogle.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	@Column(name = "userid")
	private Long userid;

	@Column(nullable = true, length = 45)
	private String street;

	@Column(nullable = true, length = 45)
	private String apt;

	@Column(nullable = true, length = 45)
	private String city;

	@Column(nullable = true, length = 45)
	private String state;

	@Column(nullable = true, length = 45)
	private String zip;

	@Column(nullable = true, length = 45)
	private String phone;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "userid")
	private User user;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, phone, state, street, user, userid, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return Objects.equals(city, other.city) && Objects.equals(phone, other.phone)
				&& Objects.equals(state, other.state) && Objects.equals(street, other.street)
				&& Objects.equals(user, other.user) && Objects.equals(userid, other.userid)
				&& Objects.equals(zip, other.zip);
	}

	@Override
	public String toString() {
		return "UserDetailsTable [userid=" + userid + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", phone=" + phone + ", user=" + user + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
