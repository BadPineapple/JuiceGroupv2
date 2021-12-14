package ilion.vitazure.enumeradores;

import ilion.admin.negocio.PropEnum;

public enum BancoEnum {

	 	NAO_INFORMADO("","", ""),
	 	ITAU("ITAU","Itaú", "341"),
	 	BB("BB","Banco do Brasil", "001"),
	 	CAIXA("CAIXA","Caixa Econômica", "104"),
	 	NUBANK("NUBANK","NuBank", "260"),
	 	BRADESCO("BRADESCO","Bradesco", "237"),
	 	SANTANDER("SANTANDER","Santander", "033"),
	 	PAN("PAN","Banco Pan", "623"),
	 	INTER("INTER","Banco Inter", "077");	

	
	private String nome;
	
	private String valor;

	private String codigo;
	
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	public String getCodigo() {return codigo;}

	private static final BancoEnum VALUES[] = BancoEnum.values();
	
	
	private BancoEnum(String nome , String valor, String codigo) {
		this.nome = nome;
		this.valor = valor;
		this.codigo = codigo;
	}
	
	public static BancoEnum fromString(String nome) {
		for (BancoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}

	public static String getCodigoFromType(String nome) {
		for (BancoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)) {
				return item.getCodigo();
			}
		}
		return null;
	}
	

}
