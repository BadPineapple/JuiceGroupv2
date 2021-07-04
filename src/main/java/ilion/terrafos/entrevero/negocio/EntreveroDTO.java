package ilion.terrafos.entrevero.negocio;

public class EntreveroDTO {
	
	private Long idPasto;
	
	private Boolean status;

	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EntreveroDTO [idPasto=" + idPasto + ", status=" + status + "]";
	}
	
}