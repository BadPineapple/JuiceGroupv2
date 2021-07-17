package ilion.vitazure.enumeradores;

public enum EspecialidadesEnum {

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

	private static final EspecialidadesEnum VALUES[] = EspecialidadesEnum.values();
	
	
	private EspecialidadesEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static EspecialidadesEnum fromString(String nome) {
		for (EspecialidadesEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
	
}
