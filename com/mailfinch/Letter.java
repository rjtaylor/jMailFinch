package com.mailfinch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.mailfinch.util.DateConverter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a letter within the MailFinch API.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class Letter {
	
	/** The {@link Configuration} object to get settings from. */
	private Configuration configuration;
	
	/** The ID assigned to this letter by MailFinch. */
	private int id;
	
	/** The time and date at which this letter was sent. */
	private Date timeSent;
	
	/** The current status of this letter. */
	private String status;
	
	/** The time and date at which this letter was purchased. */
	private Date timePurchased;
	
	/** The remote URL of the PDF document for this letter. */
	private String documentURL;
	
	/** The mailing date of this letter. */
	private Date mailingDate;
	
	/** The address of the sender. */
	private Address senderAddress;
	
	/** The address of the recipient. */
	private Address recipientAddress;

	/**
	 * Initialises a new instance of the {@link Letter} class.
	 * @param config The {@link Configuration} object to get settings from.
	 */
	public Letter(Configuration config) {
		this(config, 0);
	}

	/**
	 * Initialises a new instance of the {@link Letter} class.
	 * @param config The {@link Configuration} object to get settings from.
	 * @param id The ID assigned to this letter by MailFinch.
	 */
	public Letter(Configuration config, int id) {
		this(config, id, null);
	}

	/**
	 * Initialises a new instance of the {@link Letter} class.
	 * @param config The {@link Configuration} object to get settings from.
	 * @param id The ID assigned to this letter by MailFinch.
	 * @param timeSent The time at which this letter was sent.
	 */
	public Letter(Configuration config, int id, Date timeSent) {
		this(config, id, timeSent, null);
	}

	/**
	 * Initialises a new instance of the {@link Letter} class.
	 * @param config The {@link Configuration} object to get settings from.
	 * @param id The ID assigned to this letter by MailFinch.
	 * @param timeSent The time at which this letter was sent.
	 * @param status The current status of this letter.
	 */
	public Letter(Configuration config, int id,
				  Date timeSent, String status) {
		this(config, id, timeSent, status, null);
	}
	
	/**
	 * Initialises a new instance of the {@link Letter} class.
	 * @param config The {@link Configuration} object to get settings from.
	 * @param id The ID assigned to this letter by MailFinch.
	 * @param timeSent The time at which this letter was sent.
	 * @param status The current status of this letter.
	 * @param timePurchased The time at which this letter was purchased.
	 */
	public Letter(Configuration config, int id, Date timeSent,
				  String status, Date timePurchased) {
		this.configuration = config;
		this.id = id;
		this.timeSent = timeSent;
		this.status = status;
		this.timePurchased = timePurchased;
	}

	/**
	 * Gets the ID assigned to this letter by MailFinch.
	 * @return The ID assigned to this letter by MailFinch.
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Gets the remote URL of the PDF document for this letter.
	 * @return The remote URL of the PDF document for this letter.
	 */
	public String getDocumentURL() {
		return documentURL;
	}
	
	/**
	 * Sets the remote URL of the PDF document for this letter.
	 * @param url The remote URL of the PDF document for this letter.
	 */
	public void setDocumentURL(String url) {
		documentURL = url;
	}
	
	/**
	 * Gets the mailing date of this letter.
	 * @return The mailing date of this letter.
	 */
	public Date getMailingDate() {
		return mailingDate;
	}
	
	/**
	 * Sets the mailing date of this letter.
	 * @param mailingDate The mailing date of this letter.
	 */
	public void setMailingDate(Date mailingDate) {
		this.mailingDate = mailingDate;
	}
	
	/**
	 * Gets the address of the sender.
	 * @return The address of the sender.
	 */
	public Address getSender() {
		return senderAddress;
	}
	
	/**
	 * Sets the address of the sender.
	 * @param sender The address of the sender.
	 */
	public void setSender(Address sender) {
		senderAddress = sender;
	}

	/**
	 * Gets the address of the recipient.
	 * @return The address of the recipient.
	 */
	public Address getRecipient() {
		return recipientAddress;
	}

	/**
	 * Sets the address of the recipient.
	 * @param recipient The address of the recipient.
	 */
	public void setRecipient(Address recipient) {
		recipientAddress = recipient;
	}
	
	/**
	 * Gets the time and date at which this letter was sent.
	 * @return The time and date at which this letter was sent.
	 */
	public Date getDateSent() {
		return timeSent;
	}
	
	/**
	 * Gets the current status of this letter
	 * @return The current status of this letter.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Gets the time and date at which this letter was purchased.
	 * @return The time and date at which this letter was purchased.
	 */
	public Date getDatePurchased() {
		return timePurchased;
	}
	
	/**
	 * Determines whether or not this letter has been saved.
	 * @return <c>true</c> if this letter has been saved; otherwise, <c>false</c>.
	 */
	public boolean isSaved() {
		return (getID() != 0);
	}
	
	/**
	 * Determines whether or not this letter has been sent.
	 * @return <c>true</c> if this letter has been sent; otherwise, <c>false</c>.
	 */
	public boolean isSent() {
		return (getDateSent() != null);
	}
	
	/**
	 * Determines whether or not this letter has been purchased.
	 * @return <c>true</c> if this letter has been purchased; otherwise, <c>false</c>.
	 */
	public boolean isPurchased() {
		return (getDatePurchased() != null);
	}
	
	/**
	 * Generates a new {@link Letter} corresponding to the given JSON data.
	 * @param config The {@link Configuration} object to get settings from.
	 * @param json The JSON data representing the letter.
	 * @return The generated {@link Letter} corresponding to the given JSON data.
	 * @throws MailFinchException If invalid JSON data has been passed to this method.
	 */
	public static Letter fromJSON(Configuration config, JSONObject json) throws MailFinchException {
		try {
			// Create a new letter.
			Letter letter = new Letter(config);
			
			// Get the letter's ID, if it exists in the JSON data.
			if (!json.isNull("id")) {
				letter.id = json.getInt("id");
			}

			// Get the letter's current status, if it exists in the JSON data.
			if (!json.isNull("status_field")) {
				letter.status = json.getString("status_field");
			}

			// Get the letter's document URL, if it exists in the JSON data.
			if (!json.isNull("pdf_remote_url")) {
				letter.documentURL = json.getString("pdf_remote_url");
			}

			// Get the sender's address, if it exists in the JSON data.
			if (!json.isNull("sender")) {
				letter.senderAddress = Address.fromJSON(json.getJSONObject("sender"));
			}
			
			// Get the recipient's address, if it exists in the JSON data.
			if (!json.isNull("recipient")) {
				letter.recipientAddress = Address.fromJSON(json.getJSONObject("recipient"));
			}
			
			// Get the letter's mailing date, if it exists in the JSON data.
			if (!json.isNull("mailing_date")) {
				letter.mailingDate = DateConverter.parse(json.getString("mailing_date"));
			}
			
			// Get the letter's send date, if it exists in the JSON data.
			if (!json.isNull("sent_at")) {
				letter.timeSent = DateConverter.parse(json.getString("sent_at"));
			}
			
			// Get the letter's purchase date, if it exists in the JSON data.
			if (!json.isNull("purchased_at")) {
				letter.timePurchased = DateConverter.parse(json.getString("purchased_at"));
			}
			
			// Return the generated letter.
			return letter;
			
		} catch (JSONException e) {
			throw new MailFinchException(Resources.INVALID_JSON, e);
		}
	}
	
	/**
	 * Gets a {@link Map} representing this letter for use in the API.
	 * @return A {@link Map} representing this letter for use in the API.
	 */
	public Map<String, Object> getData() {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> letterData = new HashMap<String, Object>();
		data.put("letter", letterData);
		
		if (getDocumentURL() != null) {
			letterData.put("pdf_remote_url", getDocumentURL());
		}
		
		if (getSender() != null) {
			letterData.put("sender_attributes", getSender().getData());
		}
		
		if (getRecipient() != null) {
			letterData.put("recipient_attributes", getRecipient().getData());
		}
		
		if (getMailingDate() != null) {
			data.put("mailing_year", DateConverter.getYear(getMailingDate()));
			data.put("mailing_month", DateConverter.getMonth(getMailingDate()));
			data.put("mailing_day", DateConverter.getDay(getMailingDate()));
		}
		
		if (isSaved()) {
			data.put("id", String.valueOf(getID()));
		}
		
		return data;
	}
	
	/**
	 * Synchronises this letter with the version on the MailFinch server.
	 * @throws MailFinchException If invalid JSON data has been received from the server.
	 */
	protected void syncWithServer(String url, String method) throws MailFinchException {
		try {
			JSONObject json;
			if (method == "GET") {
				json = new JSONObject(configuration.getConnection().execute(url, method));
			} else {
				json = new JSONObject(configuration.getConnection().execute(url, method, getData()));
			}
			JSONObject letter = json.getJSONObject("letter");
			id = letter.getInt("id");
			status = letter.getString("status_field");
			timeSent = DateConverter.parse(letter.getString("sent_at"));
			timePurchased = DateConverter.parse(letter.getString("purchased_at"));
		} catch (JSONException e) {
			throw new MailFinchException(Resources.INVALID_JSON, e);
		}
	}
	
	/**
	 * Saves this letter to the MailFinch server,
	 * either by updating an existing letter or creating a new one.
	 * @throws MailFinchException If invalid JSON data has been received from the server.
	 */
	public void save() throws MailFinchException {
		if (isSaved()) {
			syncWithServer("letters/" + getID(), "PUT");
		} else {
			syncWithServer("letters", "POST");
		}
	}
	
	/**
	 * Purchases this letter from MailFinch.
	 * @throws MailFinchException If this letter has not previously been saved.
	 * @throws MailFinchException If this letter has already been purchased.
	 */
	public void purchase() throws MailFinchException {
		if (!isSaved()) {
			throw new MailFinchException(Resources.LETTER_NOT_SAVED);
		} else if (isPurchased()) {
			throw new MailFinchException(Resources.LETTER_ALREADY_PURCHASED);
		} else {
			syncWithServer("letters/" + getID() + "/purchase", "GET");
		}
	}

}
