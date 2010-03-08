package com.mailfinch;

/**
 * This class stores resources for use throughout the MailFinch library,
 * such as error messages and debug strings.
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class Resources {
	
	/** The base URL to connect to for the MailFinch API. */
	public static final String BASE_URL = "https://www.mailfinch.com/";
	
	/** Error message for use when a letter has already been purchased. */
	public static final String LETTER_ALREADY_PURCHASED
		= "This letter has already been purchased.";

	/** Error message for use when a letter has not been saved. */
	public static final String LETTER_NOT_SAVED
		= "A letter must be saved by calling save() before it can be purchased.";

	/** Error message for use when invalid JSON data has been used. */
	public static final String INVALID_JSON
		= "Unknown or invalid JSON code has been used.";

	/** Error message for use when a malformed URL has been used. */
	public static final String MALFORMED_URL
		= "An attempt was made to connect to a malformed URL.";

	/** Error message for use when an I/O error has occurred. */
	public static final String IO_EXCEPTION
		= "An I/O error occurred when communicating with the MailFinch server.";

	/** Error message for use when an HTTP 400 error code has been received. */
	public static final String ERROR_400
		= "An HTTP error occurred: 400, a bad request was made.";

	/** Error message for use when an HTTP 401 error code has been received. */
	public static final String ERROR_401
		= "An HTTP error occurred: 401, an unauthorised request was made.";

	/** Error message for use when an HTTP 403 error code has been received. */
	public static final String ERROR_403
		= "An HTTP error occurred: 403, a forbidden request was made.";

	/** Error message for use when an HTTP 404 error code has been received. */
	public static final String ERROR_404
		= "An HTTP error occurred: 404, the requested resource could not be found.";

	/** Error message for use when an HTTP 422 error code has been received. */
	public static final String ERROR_422
		= "An HTTP error occurred: 422, a server precondition has not been met.";

	/** Error message for use when an HTTP 500 error code has been received. */
	public static final String ERROR_500
		= "An HTTP error occurred: 500, an internal server error occurred.";

	/** Error message for use when an HTTP 502 error code has been received. */
	public static final String ERROR_502
		= "An HTTP error occurred: 502, bad gateway.";

	/** Error message for use when an HTTP 503 error code has been received. */
	public static final String ERROR_503
		= "An HTTP error occurred: 403, the service is currently unavailable.";

}
