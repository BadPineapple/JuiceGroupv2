package ilion.vitazure.enumeradores;

public enum StatusEnum {

	PENDENTE("Pendente"),
	PROCESSAMENTO("Processamento"),
	CONFIRMADO("Confirmado"),
	CANCELADO("Cancelado"),
	CONCLUIDO("Concluido"),
	NAO_ENCONTRATO("NO"),
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
	
	public static StatusEnum fromStringConsulta(String nome) {
		for (StatusEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome.toUpperCase())){
				return item;
			}
		}
		return StatusEnum.NAO_ENCONTRATO;
	}
	
	
}
