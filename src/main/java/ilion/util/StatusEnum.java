package ilion.util;

public enum StatusEnum {
	
	PUBLICADO("Publicado"),
	NAO_PUBLICADO("Não publicado"),
	EXCLUIDO("Excluído")
	;
	
	private String nome;
	
	private StatusEnum(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static StatusEnum fromString(String status) {
		for (StatusEnum item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
}