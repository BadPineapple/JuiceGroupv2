package ilion.terrafos.cadastros.negocio;

public enum PlanoDeAcaoStatus {
	
	EM_ANDAMENTO("Em Andamento"),
	AGUARDANDO("Aguardando"),
	CONCLUIDO("Concluído"),
	;
	
	private String nome;
	
	private PlanoDeAcaoStatus(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static PlanoDeAcaoStatus fromString(String status) {
		for (PlanoDeAcaoStatus item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}