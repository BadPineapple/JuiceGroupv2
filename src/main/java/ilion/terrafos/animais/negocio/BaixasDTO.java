package ilion.terrafos.animais.negocio;

import java.util.ArrayList;
import java.util.List;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Pasto;

public class BaixasDTO {
	
	private Long idPasto;
	
	private List<BaixaDTO> baixas;

	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public List<BaixaDTO> getBaixas() {
		return baixas;
	}

	public void setBaixas(List<BaixaDTO> baixas) {
		this.baixas = baixas;
	}

	public List<Lote> toLotes(Usuario usuario) {
		
		List<Lote> lotes = new ArrayList<>();
		
		for (BaixaDTO m : baixas) {
			
			Lote lote = m.toLote(new Pasto(idPasto), usuario);
			
			lotes.add(lote);
			
		}
		
		return lotes;
	}
	
}