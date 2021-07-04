package ilion.terrafos.animais.negocio;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;

public class FormacaoDeLoteDTO {
	
	private Long idPasto;
	
	private Long idCategoriaAnimal;
	
	private Integer qtd;
	
	private Integer pesoMedio;
	
	private LoteTipo origem;
	
	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public Long getIdCategoriaAnimal() {
		return idCategoriaAnimal;
	}

	public void setIdCategoriaAnimal(Long idCategoriaAnimal) {
		this.idCategoriaAnimal = idCategoriaAnimal;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Integer getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(Integer pesoMedio) {
		this.pesoMedio = pesoMedio;
	}
	
	public LoteTipo getOrigem() {
		return origem;
	}

	public void setOrigem(LoteTipo origem) {
		this.origem = origem;
	}

	@Override
	public String toString() {
		return "FormacaoDeLoteDTO [idPasto=" + idPasto + ", idCategoriaAnimal=" + idCategoriaAnimal + ", qtd=" + qtd
				+ "]";
	}
	
	public Lote toLote(Usuario usuario) {
		Lote lote = new Lote();
		
		lote.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
		lote.setPasto(new Pasto(idPasto));
		lote.setValor(qtd);
		lote.setTipo(origem);
		lote.setUsuario(usuario);
		
		return lote;
	}
	
	public PesoMedio toPesoMedio(Usuario usuario) {
		PesoMedio pm = new PesoMedio();
		
		pm.setCategoriaAnimal(new CategoriaAnimal(idCategoriaAnimal));
		pm.setPasto(new Pasto(idPasto));
		pm.setValor(pesoMedio);
		pm.setTipo(PesagemTipo.FORMACAO_DE_LOTE);
		pm.setUsuario(usuario);
		
		return pm;
	}
	
}