package tn.enis.member.common;

public class ExceptionSpecifique extends Exception {

	private static final long serialVersionUID = 1L;
	String message;

	public ExceptionSpecifique(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExceptionSpecifique [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
