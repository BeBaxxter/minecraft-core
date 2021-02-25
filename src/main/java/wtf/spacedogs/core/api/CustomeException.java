package wtf.spacedogs.core.api;

/**
 * CustomeException
 *
 * allows the developer to throw custome exceptions
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-18
 */

public class CustomeException extends RuntimeException {

	/**
	 * inherited from RuntimeException and throws exceptions that appears during the normal operation of the
	 * Java Virtual Machine.
	 *
	 * @param message the error that appears
	 */
	public CustomeException(String message) {
		super(message);
	}
}
