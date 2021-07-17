package ilion.vitazure.enumeradores;

public enum TemasTrabalhoEnum {

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

	private static final TemasTrabalhoEnum VALUES[] = TemasTrabalhoEnum.values();
	
	
	private TemasTrabalhoEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static TemasTrabalhoEnum fromString(String nome) {
		for (TemasTrabalhoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
