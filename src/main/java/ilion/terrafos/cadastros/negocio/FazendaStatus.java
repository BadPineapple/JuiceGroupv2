package ilion.terrafos.cadastros.negocio;

public enum FazendaStatus {
	
	ATIVA("Ativa"),
	INATIVA("Inativa"),
	;
	
	private String nome;
	
	private FazendaStatus(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static FazendaStatus fromString(String status) {
		for (FazendaStatus item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}