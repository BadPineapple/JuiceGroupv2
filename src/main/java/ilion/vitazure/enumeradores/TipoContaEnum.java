package ilion.vitazure.enumeradores;

public enum TipoContaEnum {

	CORRENTE("CORRENTE","Conta Corrente"),
	POUPANCA("POUPANCA","Conta Poupança");	
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final TipoContaEnum VALUES[] = TipoContaEnum.values();
	
	
	private TipoContaEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static TipoContaEnum fromString(String nome) {
		for (TipoContaEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
