

public class TooManyOperandsException extends RuntimeException {
	public TooManyOperandsException(String collection) {
		super("The " + collection + " has too many operands.");
	}
}
