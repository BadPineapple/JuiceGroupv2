package ilion.terrafos.animais.negocio;

public enum LoteTipo {
	
	ESTOQUE_INICIAL("Estoque Inicial"), //formação de lote
	NASCIMENTO("Nascimento"), //nascimento
	COMPRA("Compra"), //formação de lote
	TRANSFERENCIA_ENTRADA("Transferência (+)"), //formação de lote
	MOVIMENTO_DE_LOTE("Movimento de Lote"), //movimento de lote
	MORTE("Morte"),	//baixa
	VENDA("Venda"),	//baixa
	TRANSFERENCIA_SAIDA("Transferência (-)"),	//baixa
	AJUSTES("Ajustes (-)"),	//baixa
	;
	
	private String nome;
	
	private LoteTipo(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static LoteTipo fromString(String status) {
		for (LoteTipo item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}