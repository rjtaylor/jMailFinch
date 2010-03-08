package com.mailfinch;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a mailing address within the MailFinch API.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class Address {
	
	/** The name of the addressee. */
	private String name;
	
	/** The first line of the addressee's street address. */
	private String street1;
	
	/** The second line of the addressee's street address. */
	private String street2;
	
	/** The addressee's city. */
	private String city;
	
	/** The addressee's state. */
	private String state;
	
	/** The addressee's ZIP code. */
	private String zip;
	
	/** The addressee's country. */
	private String country;

	/**
	 * Initialises a new instance of the {@link Address} class.
	 */
	public Address() {
		// Do nothing.
	}

	/**
	 * Gets a {@link Map} representing this address for use in the API.
	 * @return A {@link Map} representing this address for use in the API.
	 */
	public Map<String, String> getData() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", getName());
		map.put("street1", getStreet1());
		map.put("street2", getStreet2());
		map.put("city", getCity());
		map.put("state", getState());
		map.put("zip", getZip());
		map.put("country", getCountry());
		return map;
	}
	
	/**
	 * Generates a new {@link Address} corresponding to the given JSON data.
	 * @param json The JSON data representing the address.
	 * @return The generated {@link Address} corresponding to the given JSON data.
	 * @throws MailFinchException If invalid JSON data has been received from the server.
	 */
	public static Address fromJSON(JSONObject json) throws MailFinchException {
		try {
			Address address = new Address();

			// Get the addressee's name, if it exists in the JSON data.
			if (!json.isNull("name")) {
				address.name = json.getString("name");
			}

			// Get the addressee's first street name, if it exists in the JSON data.
			if (!json.isNull("street1")) {
				address.street1 = json.getString("street1");
			}

			// Get the addressee's second street name, if it exists in the JSON data.
			if (!json.isNull("street2")) {
				address.street2 = json.getString("street2");
			}

			// Get the addressee's city, if it exists in the JSON data.
			if (!json.isNull("city")) {
				address.city = json.getString("city");
			}

			// Get the addressee's state, if it exists in the JSON data.
			if (!json.isNull("state")) {
				address.state = json.getString("state");
			}

			// Get the addressee's ZIP code, if it exists in the JSON data.
			if (!json.isNull("zip")) {
				address.zip = json.getString("zip");
			}

			// Get the addressee's country, if it exists in the JSON data.
			if (!json.isNull("country")) {
				address.country = json.getString("country");
			}

			return address;
			
		} catch (JSONException e) {
			throw new MailFinchException(Resources.INVALID_JSON, e);
		}
	}
	
	/**
	 * Checks whether or not all required fields have been entered.
	 * @return <c>true</c> if all required fields have been entered;
	 *         otherwise, <c>false</c>.
	 */
	public boolean isValid() {
		if (name == null || name.isEmpty() ||
				street1 == null || street1.isEmpty() ||
				city == null || city.isEmpty() ||
				state == null || state.isEmpty() ||
				zip == null || zip.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the name of the addressee.
	 * @return The name of the addressee.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the addressee.
	 * @param name The new addressee name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the first line of the addressee's street address.
	 * @return The first line of the addressee's street address.
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * Sets the first line of the addressee's street address.
	 * @param street1 The new first line of the addressee's street address.
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * Gets the second line of the addressee's street address.
	 * @return The second line of the addressee's street address.
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * Sets the second line of the addressee's street address.
	 * @param street2 The new second line of the addressee's street address.
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * Gets the addressee's city.
	 * @return The addressee's city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the addressee's city.
	 * @param city The new addressee's city.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the addressee's state.
	 * @return The addressee's state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the addressee's state.
	 * @param state The new addressee's state.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the addressee's ZIP code.
	 * @return The addressee's ZIP code.
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Sets the addressee's ZIP code.
	 * @param zip The new addressee's ZIP code.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Gets the addressee's country.
	 * @return The addressee's country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the addressee's country.
	 * @param country The new addressee's country.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
