package ilion.terrafos.cadastros.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PastoMapaVH {

	public static final String COR_PADRAO = "#fff";
	public static final String COR_ALERTA = "red";
	
	private Long idPasto;
	
	private String nome;
	
	private String areaTotal;
	
	private Integer totalDeAnimais = 0;
	
	private Integer pesoMedio = 0;
	
	private Integer zoom;
	
	private List<CoordenadaVH> coordenadas;
	
	private String cor = COR_PADRAO;
	
	private Boolean entrevero = false;
	
	private Map<String, Integer> alturaDasForragens = new HashMap<String, Integer>();
	
	private List<Map<String, Object>> categoriaAnimaisQtd = new ArrayList<>();
	
	private Long idForrageira;
	
	private Boolean cochoVazio = true;
	
	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public List<CoordenadaVH> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(List<CoordenadaVH> coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Map<String, Integer> getAlturaDasForragens() {
		return alturaDasForragens;
	}

	public void setAlturaDasForragens(Map<String, Integer> alturaDasForragens) {
		this.alturaDasForragens = alturaDasForragens;
	}

	public Boolean getEntrevero() {
		return entrevero;
	}

	public void setEntrevero(Boolean entrevero) {
		this.entrevero = entrevero;
	}

	public Boolean getCochoVazio() {
		return cochoVazio;
	}

	public void setCochoVazio(Boolean cochoVazio) {
		this.cochoVazio = cochoVazio;
	}

	public String getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(String areaTotal) {
		this.areaTotal = areaTotal;
	}

	public Integer getTotalDeAnimais() {
		return totalDeAnimais;
	}

	public void setTotalDeAnimais(Integer totalDeAnimais) {
		this.totalDeAnimais = totalDeAnimais;
	}

	public Integer getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(Integer pesoMedio) {
		this.pesoMedio = pesoMedio;
	}

	public Long getIdForrageira() {
		return idForrageira;
	}

	public void setIdForrageira(Long idForrageira) {
		this.idForrageira = idForrageira;
	}

	public List<Map<String, Object>> getCategoriaAnimaisQtd() {
		return categoriaAnimaisQtd;
	}

	public void setCategoriaAnimaisQtd(List<Map<String, Object>> categoriaAnimaisQtd) {
		this.categoriaAnimaisQtd = categoriaAnimaisQtd;
	}

	@Override
	public String toString() {
		return "PastoMapaVH [idPasto=" + idPasto + ", zoom=" + zoom + ", coordenadas=" + coordenadas + "]";
	}

	public void calcularAlturaDasForragens() {
		
		Integer entradaPadrao = this.alturaDasForragens.get("ENTRADA_PADRAO");
		Integer entrada = this.alturaDasForragens.get("ENTRADA");
		
		if( entrada != null && entrada < entradaPadrao ) {
			this.cor = COR_ALERTA;
			return;
		}
		
		Integer saidaPadrao = this.alturaDasForragens.get("SAIDA_PADRAO");
		Integer saida = this.alturaDasForragens.get("SAIDA");
		
		if( saida != null && saida < saidaPadrao ) {
			this.cor = COR_ALERTA;
			return;
		}
		
	}
}