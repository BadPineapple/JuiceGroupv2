package ilion.vitazure.enumeradores;

public enum BancoEnum {

	NAO_INFORMADO("",""),
	ITAU("ITAU","Itaú"),
	BB("BB","Banco do Brasil"),
	CAIXA("CAIXA","Caixa Econômica"),
	NUBANK("NUBANK","NuBank"),
	BRADESCO("BRADESCO","Bradesco"),
	SANTANDER("SANTANDER","Santander"),
	INTER("INTER","Banco Inter");	
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final BancoEnum VALUES[] = BancoEnum.values();
	
	
	private BancoEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static BancoEnum fromString(String nome) {
		for (BancoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
