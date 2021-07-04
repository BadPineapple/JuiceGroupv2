package ilion.util.contexto.autorizacao;

public class AcessoLiberadoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private AcessoLiberadoException() {
		super();
	}

	private static AcessoLiberadoException instance;
	
	public static AcessoLiberadoException getInstance() {
		
		if( instance == null ) {
			instance = new AcessoLiberadoException();
		}
		
		return instance;
	}
	
}
