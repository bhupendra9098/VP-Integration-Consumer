package com.moglix.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static long convertDateToTimestamp(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    java.util.Date parsedDate;
		try {
			parsedDate = dateFormat.parse(date);
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			return timestamp.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static long mergeDateAndTimeAndConvertToTimestamp(String date , String time){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		try {
			Date parsedDate = dateFormat.parse(date + " " + time);
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			return timestamp.getTime();
		} catch (ParseException e){
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}
	
}
