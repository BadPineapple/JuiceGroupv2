package ilion.util.exceptions;

public class PrecoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private Long idArtigoProduto;

	public PrecoNaoEncontradoException(Long idArtigoProduto) {
		super();
		this.idArtigoProduto = idArtigoProduto;
	}

	public Long getIdArtigoProduto() {
		return idArtigoProduto;
	}

	public void setIdArtigoProduto(Long idArtigoProduto) {
		this.idArtigoProduto = idArtigoProduto;
	}

	@Override
	public String toString() {
		return "PrecoNaoEncontradoException [idArtigoProduto=" + idArtigoProduto + "]";
	}
	
	
}