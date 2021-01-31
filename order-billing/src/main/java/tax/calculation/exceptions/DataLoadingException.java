package tax.calculation.exceptions;

public class DataLoadingException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataLoadingException() {
		super();
	}

	public DataLoadingException(String message) {
		super(message);
	}

	public DataLoadingException(String message, Throwable cause) {
		super(message, cause);
	}

}
