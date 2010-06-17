package com.mailfinch.api;

import java.util.HashMap;
import java.util.Map;
import com.mailfinch.Configuration;
import com.mailfinch.MailFinchException;

/**
 * Controls access to the MailFinch API.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public abstract class AbstractMailFinchConnection implements MailFinchConnection {
	
	/** The {@link Configuration} object to get settings from. */
	private Configuration configuration;

	/**
	 * Initialises a new instance of the {@link AbstractMailFinchConnection} class.
	 * @param config The {@link Configuration} object to get settings from.
	 */
	public AbstractMailFinchConnection(Configuration config) {
		configuration = config;
	}

	/**
	 * Downloads the contents of the specified MailFinch URL,
	 * and returns the results as a string of JSON data.
	 * @param url The MailFinch URL to get the contents of.
	 * @param requestMethod The request method (GET, PUT, POST, or DELETE).
	 * @param parameters The parameters for the request.
	 * If making a GET request, this must be a mapping of strings to strings,
	 * containing the URL query data. Otherwise, this mapping should contain JSON data.
	 * @return The results of the content download as a string of JSON data.
	 * @throws MailFinchException 
	 */
	protected abstract String getContents(String url, String requestMethod, Map<String, Object> parameters) throws MailFinchException;

	/**
	 * Makes a request to the specified MailFinch method.
	 * @param apiMethod The method to execute on the API.
	 * @param requestMethod The request method (GET, PUT, POST, or DELETE).
	 * @return The {@link MailFinchConnection.Response} representing the server response.
	 * @throws MailFinchException If a networking error has occurred.
	 */
	public MailFinchConnection.Response execute(String apiMethod, String requestMethod) throws MailFinchException {
		return execute(apiMethod, requestMethod, new HashMap<String, Object>());
	}
	
	/**
	 * Makes a request to the specified MailFinch method with the given parameters.
	 * @param apiMethod The method to execute on the API.
	 * @param requestMethod The request method (GET, PUT, POST, or DELETE).
	 * @param parameters The parameters for the request.
	 * If making a GET request, this must be a mapping of strings to strings,
	 * containing the URL query data. Otherwise, this mapping should contain JSON data.
	 * @return The {@link MailFinchConnection.Response} representing the server response.
	 * @throws MailFinchException If a networking error has occurred.
	 */
	public MailFinchConnection.Response execute(String apiMethod, String requestMethod, Map<String, Object> parameters) throws MailFinchException {
		String url = buildURL(apiMethod);
		parameters.put("api_key", configuration.getAPIKey());
		String response = getContents(url, requestMethod, parameters);
		return new MailFinchConnection.Response(response);
	}
 
	/**
	 * Builds the full URL for a request.
	 * @param apiMethod The method to execute on the API.
	 * @return The full URL to be called for the request.
	 */
	private String buildURL(String apiMethod) {
		return configuration.getBaseURL() + apiMethod + ".json";
	}

}
