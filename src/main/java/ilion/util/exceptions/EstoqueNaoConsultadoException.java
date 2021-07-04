package ilion.util.exceptions;

public class EstoqueNaoConsultadoException extends ValidacaoException {
	
	private static final long serialVersionUID = 1L;

	public EstoqueNaoConsultadoException() {
		super("Erro na consulta de estoque.");
	}
	
	
}
