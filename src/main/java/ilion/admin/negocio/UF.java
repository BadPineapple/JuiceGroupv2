package ilion.admin.negocio;

import java.util.Arrays;
import java.util.List;

public enum UF {
		
	AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");
	
	private String nome;
	
	private UF(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	

	public static UF fromString(String string) {
		UF retorno = null;
		
		for (UF eEnum : values()) {
			if(eEnum.toString().equalsIgnoreCase(string) || eEnum.getNome().equalsIgnoreCase(string)){
				retorno = eEnum;
			}
		}
		return retorno;
	}	
	
	public static List<UF> toList() {
		List<UF> lista = Arrays.asList(values());
		
		return lista;
	}
	
	



}
