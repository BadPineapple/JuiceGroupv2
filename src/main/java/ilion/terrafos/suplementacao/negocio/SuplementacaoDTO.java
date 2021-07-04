package ilion.terrafos.suplementacao.negocio;

public class SuplementacaoDTO {
	
	private Long idPasto;
	
	private SuplementacaoStatus status;
	
	private Long idProduto;
	
	private Float qtd;

	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public SuplementacaoStatus getStatus() {
		return status;
	}

	public void setStatus(SuplementacaoStatus status) {
		this.status = status;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Float getQtd() {
		return qtd;
	}

	public void setQtd(Float qtd) {
		this.qtd = qtd;
	}

	@Override
	public String toString() {
		return "SuplementacaoDTO [idPasto=" + idPasto + ", status=" + status + ", idProduto=" + idProduto + ", qtd="
				+ qtd + "]";
	}
	
}