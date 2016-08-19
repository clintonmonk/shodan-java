package shodan.exception;

public class ShodanException extends RuntimeException {
	
	private static final long serialVersionUID = -1518288829567816254L;

	public ShodanException() {
		super();
	}
	
	public ShodanException(String message) {
		super(message);
	}
	
}