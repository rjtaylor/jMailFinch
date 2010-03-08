package com.mailfinch;

import com.mailfinch.api.JSONConnection;
import com.mailfinch.api.MailFinchConnection;

/**
 * This class encapsulates a MailFinch configuration,
 * for use by the various classes in this library.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class Configuration {
	
	/** The unique API key to use for all MailFinch requests. */
	private final String key;
	
	/** The {@link MailFinchConnection} object to execute requests with. */
	private MailFinchConnection connection;

	/**
	 * Initialises a new instance of the {@link Configuration} class.
	 * @param key The unique API key to use for all MailFinch requests.
	 */
	public Configuration(String key) {
		this(key, null);
	}

	/**
	 * Initialises a new instance of the {@link Configuration} class.
	 * @param key The unique API key to use for all MailFinch requests.
	 * @param connection The {@link MailFinchConnection} object to execute requests with.
	 */
	public Configuration(String key, MailFinchConnection connection) {
		this.key = key;
		this.connection = connection;
	}
	
	/**
	 * Gets the base URL used to connect to the MailFinch service.
	 * @return The base URL used to connect to the MailFinch service.
	 */
	public String getBaseURL() {
		return Resources.BASE_URL;
	}
	
	/**
	 * Gets the unique API key that this object was initialised with.
	 * @return The unique API key to use for all MailFinch requests.
	 */
	public String getAPIKey() {
		return key;
	}
	
	/**
	 * Gets the current {@link MailFinchConnection} object.
	 * If one does not exist, a new default object will be created 
	 * @return The {@link MailFinchConnection} object to execute requests with.
	 */
	public MailFinchConnection getConnection() {
		if (connection == null) {
			connection = new JSONConnection(this);
		}
		return connection;
	}
	
	/**
	 * Sets the {@link MailFinchConnection} object to use for all future requests.
	 * @param connection The {@link MailFinchConnection} object to execute requests with.
	 */
	public void setRequest(MailFinchConnection connection) {
		this.connection = connection;
	}

}
