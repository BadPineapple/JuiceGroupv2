package ilion.terrafos.suplementacao.negocio;

public enum SuplementacaoStatus {
	
	VAZIO("Vazio"),
	COM_PRODUTO("Com Produto"),
	;
	
	private String nome;
	
	private SuplementacaoStatus(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static SuplementacaoStatus fromString(String status) {
		for (SuplementacaoStatus item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}