package ilion.util.exceptions;

public class PlanoDeVooJaExistenteException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;

	public PlanoDeVooJaExistenteException() {
		super("Plano de Vôo já existente.");
	}
	
	public PlanoDeVooJaExistenteException(String message) {
		super(message);
	}
	
	
}
