package ilion.terrafos.forragens.negocio;

public enum MedicaoForragemTipo {
	
	ENTRADA("Entrada"),
	SAIDA("Saída"),
	ROTINA("Aferição de Rotina"),
	;
	
	private String nome;
	
	private MedicaoForragemTipo(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static MedicaoForragemTipo fromString(String status) {
		for (MedicaoForragemTipo item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}