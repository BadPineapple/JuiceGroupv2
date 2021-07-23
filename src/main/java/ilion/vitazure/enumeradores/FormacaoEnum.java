package ilion.vitazure.enumeradores;

public enum FormacaoEnum {

	NAO_INFORMADO("",""),
	PHD("PHD","Phd"),
	MESTRADO("MESTRADO","Mestrado em Psicologia"),
	GRADUACAO("GRADUACAO","Gradução em Psicologia");
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final FormacaoEnum VALUES[] = FormacaoEnum.values();
	
	
	private FormacaoEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static FormacaoEnum fromString(String nome) {
		for (FormacaoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
