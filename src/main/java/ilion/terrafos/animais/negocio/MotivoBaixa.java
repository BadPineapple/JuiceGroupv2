package ilion.terrafos.animais.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MotivoBaixa {
	
	ACIDENTES("Acidentes", LoteTipo.MORTE),
	ATAQUE_DE_PREDADORES("Ataque de predadores", LoteTipo.MORTE),
	CLOSTRIDIOSES("Clostridioses", LoteTipo.MORTE),
	COBRA("Cobra", LoteTipo.MORTE),
	COLICA("Cólica", LoteTipo.MORTE),
	DESNUTRICAO("Desnutrição", LoteTipo.MORTE),
	DIARREIA("Diarréia", LoteTipo.MORTE),
	DOENCA_REPRODUTIVA("Doença reprodutiva", LoteTipo.MORTE),
	DOENCA_RESPIRATORIA("Doença Respiratória", LoteTipo.MORTE),
	ELETROCUSSAO("Eletrocussão", LoteTipo.MORTE),
	FRAQUEZA("Fraqueza", LoteTipo.MORTE),
	INFECCAO_ABCESSO("Infecção / abcesso", LoteTipo.MORTE),
	INTOXICACAO("Intoxicação", LoteTipo.MORTE),
	TIMPANISMO("Timpanismo", LoteTipo.MORTE),
	TRISTEZA_PARASITARIA("Tristeza parasitária", LoteTipo.MORTE),
	VERMINOSES_PARASITAS("Verminoses e parasitas", LoteTipo.MORTE),
	VENDA("Venda", LoteTipo.VENDA),	//baixa
	TRANSFERENCIA_SAIDA("Transferência (-)", LoteTipo.TRANSFERENCIA_SAIDA),	//baixa	
	AJUSTES("Ajustes (-)", LoteTipo.AJUSTES)	//baixa	
	;
			
	private String nome;
	
	private LoteTipo tipo;
	
	
	private MotivoBaixa(String nome, LoteTipo tipo){
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public LoteTipo getTipo() {
		return tipo;
	}

	public static MotivoBaixa fromString(String status) {
		for (MotivoBaixa item : values()) {
			if( item.nome.equals(status) || item.toString().equals(status)){
				return item;
			}
		}
		return null;
	}
	
	public static List<Map<String, String>> toList() {
		
		List<Map<String, String>> lista = new ArrayList<>();
		
		MotivoBaixa[] motivos = MotivoBaixa.values();
		
		for (MotivoBaixa motivoBaixa : motivos) {
			
			Map<String, String> m = new HashMap<>();
			m.put("value", motivoBaixa.name());
			m.put("label", motivoBaixa.nome);
			
			lista.add(m);
			
		}
		
		return lista;
	}
}