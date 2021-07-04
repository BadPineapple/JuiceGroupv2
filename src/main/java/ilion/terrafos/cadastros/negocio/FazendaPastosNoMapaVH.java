package ilion.terrafos.cadastros.negocio;

import java.util.List;

public class FazendaPastosNoMapaVH {
	
	private Integer zoom;
	
	private CoordenadaVH center;
	
	private List<PastoMapaVH> pastos;
	
	public FazendaPastosNoMapaVH() {
		super();
	}
	
	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public CoordenadaVH getCenter() {
		return center;
	}

	public void setCenter(CoordenadaVH center) {
		this.center = center;
	}

	public List<PastoMapaVH> getPastos() {
		return pastos;
	}

	public void setPastos(List<PastoMapaVH> pastos) {
		this.pastos = pastos;
	}

	@Override
	public String toString() {
		return "FazendaMapasVH [zoom=" + zoom + ", center=" + center + ", pastos=" + pastos + "]";
	}
	
}