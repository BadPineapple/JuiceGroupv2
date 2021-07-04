package ilion.util.exceptions;

public class EstoqueNaoDisponivelException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;

	public EstoqueNaoDisponivelException() {
		super("Há produto(s) com estoque indisponível.");
	}
	
	public EstoqueNaoDisponivelException(String message) {
		super(message);
	}
	
	
}
