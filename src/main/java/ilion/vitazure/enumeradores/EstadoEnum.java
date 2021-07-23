package ilion.vitazure.enumeradores;

public enum EstadoEnum {
	
	NAO_INFORMADO("",""),
	AC("AC","Acre"),
    AL("AL","Alagoas"),
    AP("AP","Amapá"),
    AM("AM","Amazonas"),
    BA("BA","Bahia"),
    CE("CE","Ceará"),
    DF("DF","Distrito Federal"),
    ES("ES","Espírito Santo"),
    GO("GO","Goiás"),
    MA("MA","Maranhão"),
    MT("MT","Mato Grosso"),
    MS("MS","Mato Grosso do Sul"),
    MG("MG","Minas Gerais"),
    PA("PA","Pará"),
    PB("PB","Paraíba"),
    PR("PR","Paraná"),
    PE("PE","Pernambuco"),
    PI("PI","Piauí"),
    RJ("RJ","Rio de Janeiro"),
    RN("RN","Rio Grande do Norte"),
    RS("RS","Rio Grande do Sul"),
    RO("RO","Rondônia"),
    RR("RR","Roraima"),
    SC("SC","Santa Catarina"),
    SP("SP","São Paulo"),
    SE("SE","Sergipe"),
    TO("TO","Tocantins");		
	
	private String nome;
	
	private String valor;
	
	public String getNome() {
		return nome;
	}
	
	public String getValor() {
		return valor;
	}

	private static final EstadoEnum VALUES[] = EstadoEnum.values();
	
	
	private EstadoEnum(String nome , String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public static EstadoEnum fromString(String nome) {
		for (EstadoEnum item : VALUES) {
			if( item.nome.equals(nome) || item.toString().equals(nome)){
				return item;
			}
		}
		return null;
	}
	
}
