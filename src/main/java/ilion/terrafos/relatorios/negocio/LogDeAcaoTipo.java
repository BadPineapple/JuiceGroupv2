package ilion.terrafos.relatorios.negocio;

public enum LogDeAcaoTipo {
	
	MEDICAO_DE_FORRAGEM("Medição de Forragem"),
	SUPLEMENTACAO("Suplementação"),
	MOVIMENTACAO_DE_LOTES("Movimentação de Lotes"),
	FORMACAO_DE_LOTES("Formação de Lotes"),
	NASCIMENTOS("Nascimentos"),
	BAIXAS("Baixas"),
	PESAGENS("Pesagens"),
	ENTREVEROS("ENTREVEROS"),
	;
	
	private String nome;
	
	private LogDeAcaoTipo(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static LogDeAcaoTipo fromString(String status) {
		for (LogDeAcaoTipo item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}