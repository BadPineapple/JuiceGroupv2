package ilion.terrafos.animais.negocio;

import java.util.ArrayList;
import java.util.List;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;

public class MovimentoDeLoteDTO {
	
	private Long idPastoOrigem;
	
	private Long idPastoDestino;
	
	private List<MovimentoCategoriaAnimalDTO> movimentos;
	
	private Integer alturaEntradaForrageira;
	
	public Long getIdPastoOrigem() {
		return idPastoOrigem;
	}

	public void setIdPastoOrigem(Long idPastoOrigem) {
		this.idPastoOrigem = idPastoOrigem;
	}

	public Long getIdPastoDestino() {
		return idPastoDestino;
	}

	public void setIdPastoDestino(Long idPastoDestino) {
		this.idPastoDestino = idPastoDestino;
	}
	
	public List<MovimentoCategoriaAnimalDTO> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<MovimentoCategoriaAnimalDTO> movimentos) {
		this.movimentos = movimentos;
	}

	public Integer getAlturaEntradaForrageira() {
		return alturaEntradaForrageira;
	}

	public void setAlturaEntradaForrageira(Integer alturaEntradaForrageira) {
		this.alturaEntradaForrageira = alturaEntradaForrageira;
	}

	public List<Lote> toLotes(Usuario usuario) {
		
		List<Lote> lotes = new ArrayList<>();
		
		for (MovimentoCategoriaAnimalDTO m : movimentos) {
			
			Lote loteOrigem = m.toLoteOrigem(new Pasto(idPastoOrigem), usuario);
			
			lotes.add(loteOrigem);
			
			Lote loteDestino = m.toLoteDestino(new Pasto(idPastoDestino), usuario);
			
			lotes.add(loteDestino);
			
		}
		
		return lotes;
	}
	
	public List<PesoMedio> toPesoMedios(Usuario usuario) {
		
		PesoMedioNegocio pesoMedioNegocio = SpringApplicationContext.getBean(PesoMedioNegocio.class);
		
		List<PesoMedio> pms = new ArrayList<>();
		
		for (MovimentoCategoriaAnimalDTO m : movimentos) {
			
			PesoMedio pmPastoOrigem = pesoMedioNegocio.consultarUltimoPesoMedio(new Pasto(idPastoOrigem), new CategoriaAnimal(m.getIdCategoriaAnimal()));
			
			PesoMedio pm = new PesoMedio();
			pm.setPasto(new Pasto(idPastoDestino));
			pm.setCategoriaAnimal(new CategoriaAnimal(m.getIdCategoriaAnimal()));
			pm.setTipo(PesagemTipo.MOVIMENTO_DE_LOTE);
			pm.setUsuario(usuario);
			pm.setValor(pmPastoOrigem.getValor());
			
			pms.add(pm);
			
		}
		
		return pms;
	}
	
}