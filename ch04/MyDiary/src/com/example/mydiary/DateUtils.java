package com.example.mydiary;

import java.text.SimpleDateFormat;


public class DateUtils {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private DateUtils() {}
	
	public static long nowMillis() {
		return System.currentTimeMillis();
	}
	
	public static String millis2String(long millis) {
		return new SimpleDateFormat(DATE_FORMAT).format(millis);
	}
}
