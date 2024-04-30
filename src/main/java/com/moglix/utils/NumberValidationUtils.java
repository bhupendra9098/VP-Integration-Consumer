package com.moglix.utils;

public class NumberValidationUtils {

	public static boolean isDouble(String value) {
	    try {
	        Double.parseDouble(value);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
}
