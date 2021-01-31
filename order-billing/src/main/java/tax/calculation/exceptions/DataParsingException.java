package tax.calculation.exceptions;

public class DataParsingException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataParsingException() {
		super();
	}

	public DataParsingException(String message) {
		super(message);
	}

	public DataParsingException(String message, Throwable cause) {
		super(message, cause);
	}

}
