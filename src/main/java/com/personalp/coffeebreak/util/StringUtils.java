package com.personalp.coffeebreak.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {

	public static boolean isEmpty(String str) {
		
		if(str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	public static String encrypt(String rawStr) {
		
		if(isEmpty(rawStr)) {
			return null;
		}
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(rawStr);
	}
}
