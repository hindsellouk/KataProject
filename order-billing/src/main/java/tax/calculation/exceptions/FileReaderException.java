package tax.calculation.exceptions;

public class FileReaderException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileReaderException() {
		super();
	}

	public FileReaderException(String message) {
		super(message);
	}

	public FileReaderException(String message, Throwable cause) {
		super(message, cause);
	}

}