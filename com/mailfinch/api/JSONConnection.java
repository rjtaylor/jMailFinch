package com.mailfinch.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import com.mailfinch.Configuration;
import com.mailfinch.MailFinchException;
import com.mailfinch.Resources;
import com.mailfinch.util.QueryStringBuilder;

/**
 * Performs requests to the MailFinch server using an
 * {@link HttpsURLConnection} object and JSON queries.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class JSONConnection extends AbstractMailFinchConnection {

	/**
	 * Initialises a new instance of the {@link JSONConnection} class.
	 * @param config The {@link Configuration} object to get settings from.
	 */
	public JSONConnection(Configuration config) {
		super(config);
	}
	
	/**
	 * Checks for HTTP error codes and throws exceptions if necessary.
	 * @param code The HTTP response code from the server.
	 * @throws MailFinchException If certain HTTP error codes have been detected.
	 */
	private void throwExceptionByCode(int code) throws MailFinchException {
		switch (code) {
			case 400:
				throw new MailFinchException(Resources.ERROR_400);
			case 401:
				throw new MailFinchException(Resources.ERROR_401);
			case 403:
				throw new MailFinchException(Resources.ERROR_403);
			case 404:
				throw new MailFinchException(Resources.ERROR_404);
			case 422:
				throw new MailFinchException(Resources.ERROR_422);
			case 500:
				throw new MailFinchException(Resources.ERROR_500);
			case 502:
				throw new MailFinchException(Resources.ERROR_502);
			case 503:
				throw new MailFinchException(Resources.ERROR_503);
		}
	}

	@Override
	protected String getContents(String url, String requestMethod, Map<String, Object> parameters) throws MailFinchException {
		// Set up some variables.
		String query = null;
		HttpsURLConnection con = null;
		
		// Build the query string or JSON data.
		if (requestMethod == "GET") {
			url += QueryStringBuilder.build(parameters);
		} else {
			query = new JSONObject(parameters).toString();
		}
		
		try {
			// Create a new HTTP connection to the MailFinch server.
			con = (HttpsURLConnection)(new URL(url).openConnection());
			con.setRequestMethod(requestMethod);
			con.setRequestProperty("Content-Type", "application/json");
			
			// Add POST fields if necessary. 
			if (requestMethod != "GET") {
				con.setDoOutput(true);
				con.setRequestProperty("Content-Length", Integer.toString(query.getBytes().length));
				DataOutputStream stream = new DataOutputStream(con.getOutputStream());
				stream.writeBytes(query);
				stream.flush();
				stream.close();
			}
			
			// Connect to the server.
			con.connect();
			
			// Read the server response.
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			rd.close();
			String response = builder.toString();
			
			// Check for error codes from the server.
			throwExceptionByCode(con.getResponseCode());
			
			// Disconnect from the server and return the received JSON data.
			con.disconnect();
			return response;
			
		} catch (MalformedURLException e) {
			throw new MailFinchException(Resources.MALFORMED_URL, e);
			
		} catch (IOException e) {
			throw new MailFinchException(Resources.IO_EXCEPTION, e);
			
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		
	}

}
