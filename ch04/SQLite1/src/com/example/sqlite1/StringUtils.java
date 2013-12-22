package com.example.sqlite1;

public class StringUtils {

	private StringUtils() {}
	
	public static boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
	
}
