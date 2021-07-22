package ilion.vitazure.enumeradores;

public enum DuracaoAtendimentoEnum {
	
	NAO_INFORMADO("",""),
	DEZ_MINUTOS("10","10 Minutos"),
	VINTE_MINUTOS("20","20 Minutos"),
	TRINTA_MINUTOS("30","30 Minutos"),
	QUARENTA_MINUTOS("40","40 Minutos"),
	CINQUENTA_MINUTOS("50","50 Minutos"),
	SESSETA_MINUTOS("60","60 Minutos"),
	SETENTA_MINUTOS("70","70 Minutos");	
	
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
