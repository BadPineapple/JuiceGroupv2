package ilion.util.busca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ilion.util.Uteis;

public class PalavrasChaveCondicoes {
	
	private String q;
	
	private List<String> condicoes = new ArrayList<>();
	
	public static PalavrasChaveCondicoes nova() {
		
		PalavrasChaveCondicoes p = new PalavrasChaveCondicoes();
		
		return p;
	}
	
	public PalavrasChaveCondicoes comPalavrasChave(String palavrasChave) {
		
		this.q = palavrasChave;
		
		return this;
	}
	
	public List<String> gerar() {
		
		if( Uteis.ehNuloOuVazio(q) ) {
			return Collections.emptyList();
		}
		
		q = BuscaUtil.ajustar(q);
		
		String[] palavrasVetor = q.split(" ");
		
		List<String> lista = Arrays.asList(palavrasVetor);
		
		for (int i = 0; i < 10; i++) {
			
			Collections.shuffle(lista);
			
			adicionarCondicao(lista);
			
		}
		
		return condicoes;
	}
	
	private void adicionarCondicao(List<String> lista) {
		
		String s = lista.toString();
		
		s = s.replaceAll("\\s", "%");
		s = s.replaceAll("[\\[\\],]+", "");
		
		s = "%"+s+"%";
		
		if( condicoes.contains(s) ) {
			return;
		}
		
		condicoes.add( s );
	}
}