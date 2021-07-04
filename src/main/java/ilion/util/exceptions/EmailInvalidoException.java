package ilion.util.exceptions;

public class EmailInvalidoException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;
	
	public EmailInvalidoException() {
		super("Email inválido.");
	}
	
	public EmailInvalidoException(String m) {
		super(m);
	}
	
}