package ilion.vitazure.enumeradores;

public enum TipoProfissionalEnum {
	
	NAO_INFORMADO("",""),
	PSICOLOGO("PSICOLOGO","Psicólogo"),
	PSIQUIATRA("PSIQUIATRA","Psiquiatra"),
	MEDICO("MEDICO","Médico");	
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final TipoProfissionalEnum VALUES[] = TipoProfissionalEnum.values();
	
	
	private TipoProfissionalEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static TipoProfissionalEnum fromString(String nome) {
		for (TipoProfissionalEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}


