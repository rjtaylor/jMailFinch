package com.mailfinch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class that provides functionality to convert between date formats.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class DateConverter {
	
	/** A constant defining the format for MailFinch dates. */
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * Converts a date represented as a string in the form
	 * "yyyy-MM-ddTHH:mm:ssZ" to a {@link Date} object,
	 * or null if the date string is not in a valid format.
	 * @param date The string containing the date to convert.
	 * @return The date converted into a {@link Date} object.
	 */
	public static Date parse(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Converts a {@link Date} object into a string in the form "yyyy-MM-ddTHH:mm:ssZ".
	 * @param date The {@link Date} object to convert.
	 * @return The date converted into a string.
	 */
	public static String parse(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(date);
	}
	
	/**
	 * Gets the four digit year from the specified {@link Date} object as a string.
	 * @param date The {@link Date} object to convert.
	 * @return The four digit year as a string.
	 */
	public static String getYear(Date date) {
		return new SimpleDateFormat("yyyy").format(date);
	}
	
	/**
	 * Gets the two digit month from the specified {@link Date} object as a string.
	 * @param date The {@link Date} object to convert.
	 * @return The two digit month as a string.
	 */
	public static String getMonth(Date date) {
		return new SimpleDateFormat("MM").format(date);
	}
	
	/**
	 * Gets the two digit day from the specified {@link Date} object as a string.
	 * @param date The {@link Date} object to convert.
	 * @return The two digit day as a string.
	 */
	public static String getDay(Date date) {
		return new SimpleDateFormat("dd").format(date);
	}

}
