package ilion.vitazure.enumeradores;

public enum TempoAntecendenciaEnum {
	
	NAO_INFORMADO("",""),
	UMA("1","1 Hora"),
	DUAS("2","2 Horas"),
	TREIS("3","3 Horas");
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final TempoAntecendenciaEnum VALUES[] = TempoAntecendenciaEnum.values();
	
	
	private TempoAntecendenciaEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static TempoAntecendenciaEnum fromString(String nome) {
		for (TempoAntecendenciaEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
