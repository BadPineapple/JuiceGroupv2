package ilion.util.exceptions;

public class ValidacaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ValidacaoException() {
		super();
	}

	public ValidacaoException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+": "+getMessage();
	}

	
}
