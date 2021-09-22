package ilion.vitazure.enumeradores;

public enum SituacaoAprovacaoProfissionalEnum {

	PENDENTE("Pendente"),
	AUTORIZADO("Autorizado"),
	NAO_AUTORIZADO("Não Autorizado");	
	private String nome;
	
	
	public String getNome() {
		return nome;
	}

	private static final SituacaoAprovacaoProfissionalEnum VALUES[] = SituacaoAprovacaoProfissionalEnum.values();
	
	
	private SituacaoAprovacaoProfissionalEnum(String nome) {
		this.nome = nome;
	}
	
	public static SituacaoAprovacaoProfissionalEnum fromString(String nome) {
		for (SituacaoAprovacaoProfissionalEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
