package com.groupproject.boogle.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** UserDetails interface is for core user information.
 * Since we use user's email as id and 
 * need to use UserDetails object for loadUserByUsername 
 * method in the CustomUserDetailsService class, 
 * we need to customize the UserDetails **/
@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	// there's no need to collect all the users for now, so just leave it for now.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	public String getFullname() {
		StringBuilder fullname = new StringBuilder(user.getFirstname());
		fullname.append(" ");
		fullname.append(user.getLastname());
		
		return fullname.toString();
	}
	
	public String getPhone() {
		return user.getUserDetailsTable().getPhone();
	}
	
	public String getStreet() {
		return user.getUserDetailsTable().getStreet();
	}
	
	public String getCity() {
		return user.getUserDetailsTable().getCity();
	}
	
	public String getState() {
		return user.getUserDetailsTable().getState();
	}
	
	public String getZip() {
		return user.getUserDetailsTable().getZip();
	}

	/** those four methods below are for user's authentication **/
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
