package ilion.vitazure.enumeradores;

public enum DuracaoAtendimentoEnum {
	
	NAO_INFORMADO("",""),
	QUARENTA_MINUTOS("40","40 Minutos"),
	CINQUENTA_MINUTOS("50","50 Minutos"),
	SESSETA_MINUTOS("60","60 Minutos");
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final DuracaoAtendimentoEnum VALUES[] = DuracaoAtendimentoEnum.values();
	
	
	private DuracaoAtendimentoEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static DuracaoAtendimentoEnum fromString(String nome) {
		for (DuracaoAtendimentoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
	public String getNomeApresentar() {
		return getValor();
	}
	
}
