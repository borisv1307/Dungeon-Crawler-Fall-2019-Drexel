package exceptions;

public class LogicError extends RuntimeException {

	private static final long serialVersionUID = -928574799929240354L;

	public LogicError(String errorMessage) {
		super(errorMessage);
	}

}