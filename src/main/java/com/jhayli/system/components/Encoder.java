package com.jhayli.system.components;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encoder {
	public String encodePassword(String password) {
	
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		return bCryptPasswordEncoder.encode(password);
	
	}
	
	public boolean decode(String decode, String numero) {
	
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		return bCryptPasswordEncoder.matches(numero, decode);
	}
}
