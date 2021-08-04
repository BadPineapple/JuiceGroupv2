package ilion.vitazure.enumeradores;

public enum StatusEnum {

	ANDAMENTO("Andamento"),
	PROCESSAMENTO("Processamento"),
	CONFIRMADO("Confirmado"),
	CANCELADO("Cancelado"),
	CONCLUIDO("Concluido"),
	REMARCADO("Remarcado");
	
	private String nome;
	
	
	public String getNome() {
		return nome;
	}

	private static final StatusEnum VALUES[] = StatusEnum.values();
	
	
	private StatusEnum(String nome) {
		this.nome = nome;
	}
	
	public static StatusEnum fromString(String nome) {
		for (StatusEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
	
}
