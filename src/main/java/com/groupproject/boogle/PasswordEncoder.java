package com.groupproject.boogle;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword1 = "0000";
		String rawPassword2 = "1234";
		String encodePassword1 = encoder.encode(rawPassword1);
		String encodePassword2 = encoder.encode(rawPassword2);
		
		System.out.println(encodePassword1);
		System.out.println(encodePassword2);

	}

}
