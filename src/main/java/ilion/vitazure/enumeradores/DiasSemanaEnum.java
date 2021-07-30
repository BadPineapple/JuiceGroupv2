package ilion.vitazure.enumeradores;

public enum DiasSemanaEnum {

	DOMINGO(0,"Domingo"),
	SEGUNDA(1,"Segunda - Feira"),
	TERCA(2,"Terça - Feira"),
	QUARTA(3,"Quarta - Feira"),
	QUINTA(4,"Quinta - Feira"),
	SEXTA(5,"Sexta - Feira"),
	SABADO(6,"Sabado");
	
	private int value;
	
	private String valor;
	
	public int getValue() {
		return value;
	}

	public String getValor() {
		return valor;
	}

	private static final DiasSemanaEnum VALUES[] = DiasSemanaEnum.values();
	
	
	private DiasSemanaEnum(int value , String valor) {
		this.value = value;
		this.valor = valor;
	}
	
	public static DiasSemanaEnum fromString(String valor) {
		for (DiasSemanaEnum item : VALUES) {
			if( item.valor.equals(valor) || item.toString().equals(valor)){
				return item;
			}
		}
		return null;
	}
	
	public String getNomeApresentar() {
		return getValor();
	}
	
}
