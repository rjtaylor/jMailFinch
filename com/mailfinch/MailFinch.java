package com.mailfinch;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.mailfinch.api.AbstractMailFinchConnection;
import com.mailfinch.api.JSONConnection;
import com.mailfinch.api.MailFinchConnection;

/**
 * The main class within the MailFinch library,
 * this provides you with access to everything you need.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class MailFinch {
	
	/** The {@link Configuration} object to get settings from. */
	private Configuration configuration;
	
	/** The {@link AbstractMailFinchConnection} object to use for accessing the MailFinch API. */
	private AbstractMailFinchConnection api; 

	/**
	 * Initialises a new instance of the {@link MailFinch} class.
	 * @param key The unique API key to use for all MailFinch requests.
	 */
	public MailFinch(String key) {
		this(new Configuration(key));
	}
	
	/**
	 * Initialises a new instance of the {@link MailFinch} class.
	 * @param config The {@link Configuration} object to get settings from.
	 */
	public MailFinch(Configuration config) {
		configuration = config;
	}
	
	/**
	 * Gets the {@link Configuration} object containing the current settings.
	 * @return The {@link Configuration} object containing the current settings.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
	
	/**
	 * Sets the {@link Configuration} object to get settings from.
	 * @param config The {@link Configuration} object to get settings from.
	 */
	public void setConfiguration(Configuration config) {
		configuration = config;
	}
	
	/**
	 * Gets the {@link AbstractMailFinchConnection} object to use for accessing the MailFinch API.
	 * @return The {@link AbstractMailFinchConnection} object to use for accessing the MailFinch API.
	 */
	public AbstractMailFinchConnection getAPI() {
		if (api == null) {
			api = new JSONConnection(getConfiguration());
		}
		return api;
	}
	
	/**
	 * Gets a new instance of {@link Letter},
	 * configured for the current API key.
	 * @return A new instance of {@link Letter}.
	 */
	public Letter newLetter() {
		return new Letter(getConfiguration());
	}
	
	/**
	 * Gets all MailFinch letters for the current API key.
	 * @return An {@link ArrayList} containing all the {@link Letter} objects.
	 * @throws MailFinchException If invalid JSON data has been received from the server.
	 */
	public ArrayList<Letter> getAllLetters() throws MailFinchException {
		try {
			MailFinchConnection.Response response = getAPI().execute("letters", "GET");
			JSONArray array = response.getArray();
			ArrayList<Letter> letters = new ArrayList<Letter>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject letter = array.getJSONObject(i);
				letters.add(Letter.fromJSON(getConfiguration(), letter.getJSONObject("letter")));
			}
			return letters;
		} catch (JSONException e) {
			throw new MailFinchException(Resources.INVALID_JSON, e);
		}
	}
	
	/**
	 * Gets the MailFinch letter with the specified ID for the current API key. 
	 * @param id The ID of the letter to retrieve.
	 * @return The retrieved MailFinch letter as a {@link Letter} object.
	 * @throws MailFinchException If invalid JSON data has been received from the server.
	 */
	public Letter getLetter(int id) throws MailFinchException {
		try {
			MailFinchConnection.Response response = getAPI().execute("letters/" + id, "GET");
			return Letter.fromJSON(getConfiguration(), response.getObject().getJSONObject("letter"));
		} catch (JSONException e) {
			throw new MailFinchException(Resources.INVALID_JSON, e);
		}
	}

}
