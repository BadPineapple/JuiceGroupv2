package ilion.terrafos.cadastros.negocio;

public enum Sexo {
	
	MACHO("Macho"),
	FEMEA("Fêmea"),
	;
	
	private String nome;
	
	private Sexo(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static Sexo fromString(String status) {
		for (Sexo item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}