package ilion.util;

import java.util.Collections;
import java.util.List;

public class ListaVH {
	
	private List<? extends Object> lista;
	
	private ListaVH() {
		super();
	}

	public List<? extends Object> getLista() {
		return lista;
	}

	public void setLista(List<? extends Object> lista) {
		this.lista = lista;
	}
	
	public static ListaVH from(List<? extends Object> lista) {
		
		if( lista == null ) {
			lista = Collections.emptyList();
		}
		
		ListaVH listaDTO = new ListaVH();
		
		listaDTO.setLista(lista);
		
		return listaDTO;
	}
	
}