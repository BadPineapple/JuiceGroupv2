package ilion.me.pagar;

public enum CustomerDocumentType {
	NONE("none"),cpf("cpf"),cnpj("cnpj"),passport("passport"),other("other");
	private String type;

	CustomerDocumentType(String type){
		this.type=type;
	}
	
	@Override
	public String toString() {
	return type;
	}
};

