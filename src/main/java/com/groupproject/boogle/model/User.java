package com.groupproject.boogle.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/** @Entity and @Table annotations specify that this class is an entity 
 * and the "users" table is used for mapping **/
@Entity
@Table(name = "users")
public class User {
	
    @Id // primary key setting
    @GeneratedValue(strategy=GenerationType.IDENTITY) // auto increment setting
    private Long userid;
	
    // email column must not be null and same, and data type is varchar(45).
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards;
	
	/** getters and setters **/
	public UserInfo getUserDetailsTable() {
		return userInfo;
	}

	public void setUserDetailsTable(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Long getUserid() {
		return userid;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFullname() {
		return firstname + " " + lastname;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstname, lastname, password, userid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(password, other.password)
				&& Objects.equals(userid, other.userid);
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", email=" + email + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
	
}
