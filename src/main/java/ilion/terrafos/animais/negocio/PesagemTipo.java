package ilion.terrafos.animais.negocio;

public enum PesagemTipo {
	
	FORMACAO_DE_LOTE("Formação de lote"),
	MOVIMENTO_DE_LOTE("Movimento de lote"),
	BALANCA("Balança"),
	VISUAL("Visual"),
	;
	
	private String nome;
	
	private PesagemTipo(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static PesagemTipo fromString(String status) {
		for (PesagemTipo item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}