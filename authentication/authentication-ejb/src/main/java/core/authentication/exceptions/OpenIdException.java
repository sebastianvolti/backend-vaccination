package core.authentication.exceptions;

public class OpenIdException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 9195094526474860270L;

	/**
	 *
	 */
	public OpenIdException() {
		super();
	}

	/**
	 * @param message
	 */
	public OpenIdException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OpenIdException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OpenIdException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public OpenIdException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}