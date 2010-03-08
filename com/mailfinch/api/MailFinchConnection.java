package com.mailfinch.api;

import java.util.Map;

import com.mailfinch.MailFinchException;

/**
 * Defines the interface for classes that can
 * communicate with and provide access to the MailFinch API.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public interface MailFinchConnection {

	/**
	 * Makes a request to the specified MailFinch method.
	 * @param apiMethod The method to execute on the API.
	 * @param requestMethod The request method (GET, PUT, POST, or DELETE).
	 * @throws MailFinchException If a networking error has occurred.
	 */
	public String execute(String apiMethod, String requestMethod) throws MailFinchException;
	
	/**
	 * Makes a request to the specified MailFinch method with the given parameters.
	 * @param apiMethod The method to execute on the API.
	 * @param requestMethod The request method (GET, PUT, POST, or DELETE).
	 * @param parameters The parameters for the request.
	 * If making a GET request, this must be a mapping of strings to strings,
	 * containing the URL query data. Otherwise, this mapping should contain JSON data.
	 * @throws MailFinchException If a networking error has occurred.
	 */
	public String execute(String apiMethod, String requestMethod, Map<String, Object> parameters) throws MailFinchException;

}
