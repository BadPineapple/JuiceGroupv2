package ilion.terrafos.animais.negocio;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;

public class BaixaDTO {
	
	private Long idCategoriaAnimal;
	
	private Integer valor;
	
	private MotivoBaixa motivoBaixa;
	
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

	public MotivoBaixa getMotivoBaixa() {
		return motivoBaixa;
	}

	public void setMotivoBaixa(MotivoBaixa motivoBaixa) {
		this.motivoBaixa = motivoBaixa;
	}
	
	public Lote toLote(Pasto pasto, Usuario usuario) {
		
		Lote lote = new Lote();
		
		lote.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
		lote.setPasto(pasto);
		lote.setUsuario(usuario);
		lote.setMotivoBaixa(motivoBaixa);
		
		LoteTipo tipo = motivoBaixa == null ? LoteTipo.MORTE : motivoBaixa.getTipo();
		lote.setTipo(tipo);
		
		lote.setValor(valor*-1);
		
		return lote;
	}

	@Override
	public String toString() {
		return "BaixaDTO [idCategoriaAnimal=" + idCategoriaAnimal + ", valor=" + valor + ", motivoBaixa=" + motivoBaixa
				+ "]";
	}
}