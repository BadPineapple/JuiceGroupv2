package ilion.terrafos.animais.negocio;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;

public class PesoMedioDTO {
	
	private PesagemTipo tipo;
	
	private Long idCategoriaAnimal;
	
	private Integer valor;
	
	public PesagemTipo getTipo() {
		return tipo;
	}

	public void setTipo(PesagemTipo tipo) {
		this.tipo = tipo;
	}

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
	
	public PesoMedio toPesoMedio(Pasto pasto, Usuario usuario) {
		PesoMedio pm = new PesoMedio();
		
		pm.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
		pm.setPasto(pasto);
		pm.setValor(valor);
		pm.setTipo(tipo);
		pm.setUsuario(usuario);
		
		return pm;
	}
	
}