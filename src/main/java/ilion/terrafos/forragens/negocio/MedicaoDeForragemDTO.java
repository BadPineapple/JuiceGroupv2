package ilion.terrafos.forragens.negocio;

public class MedicaoDeForragemDTO {
	
	private Long idPasto;
	
	private MedicaoForragemTipo tipo;
	
	private Integer altura;

	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public MedicaoForragemTipo getTipo() {
		return tipo;
	}

	public void setTipo(MedicaoForragemTipo tipo) {
		this.tipo = tipo;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "MedicaoDeForragemDTO [idPasto=" + idPasto + ", tipo=" + tipo + ", altura=" + altura + "]";
	}
	
}