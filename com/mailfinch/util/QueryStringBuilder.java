package com.mailfinch.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A utility class that provides functionality to build URL query strings.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public final class QueryStringBuilder {
	
	/**
	 * Builds a URL query string from a {@link Map} of parameters.
	 * @param parameters The {@link Map} containing the query parameters.
	 * @return The generated query string.
	 */
	public static String build(Map<String, Object> parameters) {
		StringBuilder query = new StringBuilder("?");
		try {
			for (Entry<String, Object> pair : parameters.entrySet()) {
				query.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
				query.append("=");
				query.append(URLEncoder.encode((String)pair.getValue(), "UTF-8"));
				query.append("&");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		query.deleteCharAt(query.length() - 1);
		return query.toString();
	}

}
