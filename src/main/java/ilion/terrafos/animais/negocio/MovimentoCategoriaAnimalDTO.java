package ilion.terrafos.animais.negocio;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;

public class MovimentoCategoriaAnimalDTO {
	
	private Long idCategoriaAnimal;
	
	private Integer valor;
	
	public Long getIdCategoriaAnimal() {
		return idCategoriaAnimal;
	}

	public void setIdCategoriaAnimal(Long idCategoriaAnimal) {
		this.idCategoriaAnimal = idCategoriaAnimal;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Lote toLoteOrigem(Pasto pastoOrigem, Usuario usuario) {
		
		Lote lote = new Lote();
		
		lote.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
		lote.setPasto(pastoOrigem);
		lote.setValor(valor*-1);
		lote.setTipo(LoteTipo.MOVIMENTO_DE_LOTE);
		lote.setUsuario(usuario);
		
		return lote;
	}
	
	public Lote toLoteDestino(Pasto pastoDestino, Usuario usuario) {
		
		Lote lote = new Lote();
		
		lote.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
		lote.setPasto(pastoDestino);
		lote.setValor(valor);
		lote.setTipo(LoteTipo.MOVIMENTO_DE_LOTE);
		lote.setUsuario(usuario);
		
		return lote;
	}
	
//	public PesoMedio toPesoMedio(Pasto pasto, Usuario usuario) {
//		PesoMedio pm = new PesoMedio();
//		
//		pm.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
//		pm.setPasto(pasto);
//		pm.setValor(valor);
//		pm.setTipo(tipo);
//		pm.setUsuario(usuario);
//		
//		return pm;
//	}
	
}