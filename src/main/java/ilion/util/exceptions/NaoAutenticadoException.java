package ilion.util.exceptions;

public class NaoAutenticadoException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;
	
	public NaoAutenticadoException() {
		super("E-mail/Senha inválidos.");
	}

	
	
}
