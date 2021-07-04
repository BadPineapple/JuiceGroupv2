package ilion.terrafos.animais.negocio;

import java.util.ArrayList;
import java.util.List;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;

public class PesagensDTO {
	
	private Long idPasto;
	
	private List<PesoMedioDTO> pesagens;
	
	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public List<PesoMedioDTO> getPesagens() {
		return pesagens;
	}

	public void setPesagens(List<PesoMedioDTO> pesagens) {
		this.pesagens = pesagens;
	}
	
	public List<PesoMedio> toPesosMedios(Usuario usuario) {
		
		List<PesoMedio> pesos = new ArrayList<>();
		
		for (PesoMedioDTO pmDTO : pesagens) {
			
			PesoMedio pm = new PesoMedio();
			
			pm.setCategoriaAnimal(new CategoriaAnimal(pmDTO.getIdCategoriaAnimal()));
			pm.setPasto(new Pasto(idPasto));
			pm.setValor(pmDTO.getValor());
			pm.setTipo(pmDTO.getTipo());
			pm.setUsuario(usuario);
			
			pesos.add(pm);
			
		}
		
		return pesos;
	}
	
}