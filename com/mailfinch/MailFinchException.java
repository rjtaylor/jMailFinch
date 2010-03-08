package com.mailfinch;

/**
 * MailFinchException handles all exceptions that could be thrown
 * by this library and that should be handled in client software. 
 * @author Richard Taylor <contact@rtaylor.me.uk>
 */
public class MailFinchException extends Exception {

	/**	The universal version identifier of this class. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@link MailFinchException}
	 * without any description of what caused the exception.
	 */
	public MailFinchException() {
		super();
	}

	/**
	 * Constructs a new {@link MailFinchException}
	 * with a description that will be displayed when it is thrown.
	 * @param message The description of this exception.
	 */
	public MailFinchException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link MailFinchException}
	 * with the cause of the exception.
	 * @param cause The cause of this exception.
	 */
	public MailFinchException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link MailFinchException} with a description
	 * that will be displayed when it is thrown, and the cause of this exception.
	 * @param message The description of this exception.
	 * @param cause The cause of this exception.
	 */
	public MailFinchException(String message, Throwable cause) {
		super(message, cause);
	}

}
