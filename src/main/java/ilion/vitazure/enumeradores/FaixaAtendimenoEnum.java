package ilion.vitazure.enumeradores;

public enum FaixaAtendimenoEnum {

	RELACIONAMENTO("RELACIONAMENTO","relacionamento"),
	PSICOPEDAGOGIA("PSICOPEDAGOGIA","Psicopedagogia"),
	PSICOMOTRICIDADE("PSICOMOTRICIDADE","Psicomotricidade");	
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final FaixaAtendimenoEnum VALUES[] = FaixaAtendimenoEnum.values();
	
	
	private FaixaAtendimenoEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static FaixaAtendimenoEnum fromString(String nome) {
		for (FaixaAtendimenoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
