package ilion.util.exceptions;

public class SlugJaExistenteException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;
	
	public SlugJaExistenteException() {
		super("Nome já existente.");
	}
	
}
