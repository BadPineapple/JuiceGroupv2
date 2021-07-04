package ilion.util.exceptions;

public class StatusEnumNuloException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;

	public StatusEnumNuloException() {
		super("Status deve ser preenchido.");
	}
}
