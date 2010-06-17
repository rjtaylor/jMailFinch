package com.mailfinch.api;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.mailfinch.MailFinchException;
import com.mailfinch.Resources;

/**
 * Defines the interface for classes that can
 * communicate with and provide access to the MailFinch API.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public interface MailFinchConnection {
	
	/**
	 * Represents a response from the MailFinch server.
	 * @author Richard Taylor <contact@rtaylor.me.uk>
	 */
	public class Response {
		
		/** The three digit code indicating the API status of the response. */
		private int statusCode;
		
		/** A short user-readable message explaining the API status. */
		private String message;
		
		/** The string containing the JSON values of the new MailFinch object. */
		private String object;

		/**
		 * Initialises a new instance of the {@link Response} class.
		 * @param response The response received from the server.
		 * @throws MailFinchException If invalid JSON data has been received from the server.
		 * @throws MailFinchException If the server responded with an error. See the exception's message in this case.
		 */
		public Response(String response) throws MailFinchException {
			try {
				JSONObject json = new JSONObject(response).getJSONObject("response");
				if (!json.isNull("errors")) {
					throw new MailFinchException(json.getString("errors")); 
				}
				this.statusCode = json.getInt("code");
				this.message = json.getString("message");
				this.object = json.getString("object");
			} catch (JSONException e) {
				throw new MailFinchException(Resources.INVALID_JSON, e);
			}
		}
		
		/**
		 * Gets the three digit code indicating the API status of this response.
		 * @return The three digit code indicating the API status of this response.
		 */
		public int getStatusCode() {
			return statusCode;
		}
		
		/**
		 * Gets a short user-readable message explaining the API status.
		 * @return A short user-readable message explaining the API status.
		 */
		public String getMessage() {
			return message;
		}
		
		/**
		 * Gets the {@link JSONObject} containing the values of the new MailFinch object.
		 * @return The {@link JSONObject} containing the values of the new MailFinch object.
		 * @throws JSONException If invalid JSON data has been received from the server.
		 */
		public JSONObject getObject() throws JSONException {
			return new JSONObject(object);
		}
		
		/**
		 * Gets the {@link JSONArray} containing the values of the new MailFinch object.
		 * @return The {@link JSONArray} containing the values of the new MailFinch object.
		 * @throws JSONException If invalid JSON data has been received from the server.
		 */
		public JSONArray getArray() throws JSONException {
			return new JSONArray(object);
		}

	}

	/**
	 * Makes a request to the specified MailFinch method.
	 * @param apiMethod The method to execute on the API.
	 * @param requestMethod The request method (GET, PUT, POST, or DELETE).
	 * @return The {@link MailFinchConnection.Response} representing the server response.
	 * @throws MailFinchException If a networking error has occurred.
	 */
	public MailFinchConnection.Response execute(String apiMethod, String requestMethod) throws MailFinchException;
	
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
	public MailFinchConnection.Response execute(String apiMethod, String requestMethod, Map<String, Object> parameters) throws MailFinchException;

}
