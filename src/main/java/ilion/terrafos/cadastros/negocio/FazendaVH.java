package ilion.terrafos.cadastros.negocio;

import java.util.List;

public class FazendaVH {
	
	private Long id;
	
	private String nome;
	
	private String proprietario;
	
	private Float areaTotal;
	
	private String telefone;
	
	private String municipio;
	
	private String uf;
	
	private Float safra;
	
	private Float entreSafra;
	
	private Integer pluviometriaJan;
	
	private Integer pluviometriaFev;
	
	private Integer pluviometriaMar;
	
	private Integer pluviometriaAbr;
	
	private Integer pluviometriaMai;
	
	private Integer pluviometriaJun;
	
	private Integer pluviometriaJul;
	
	private Integer pluviometriaAgo;
	
	private Integer pluviometriaSet;
	
	private Integer pluviometriaOut;
	
	private Integer pluviometriaNov;
	
	private Integer pluviometriaDez;
	
	private Integer pluviometriaAcumulado;
	
	private FazendaStatus status;
	
	private List<Retiro> retiros;
	
	private List<CategoriaAnimal> categoriasDeAnimais;
	
	private List<Raca> racas;
	
	private List<Produto> produtos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public Float getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(Float areaTotal) {
		this.areaTotal = areaTotal;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Float getSafra() {
		return safra;
	}

	public void setSafra(Float safra) {
		this.safra = safra;
	}

	public Float getEntreSafra() {
		return entreSafra;
	}

	public void setEntreSafra(Float entreSafra) {
		this.entreSafra = entreSafra;
	}

	public Integer getPluviometriaJan() {
		return pluviometriaJan;
	}

	public void setPluviometriaJan(Integer pluviometriaJan) {
		this.pluviometriaJan = pluviometriaJan;
	}

	public Integer getPluviometriaFev() {
		return pluviometriaFev;
	}

	public void setPluviometriaFev(Integer pluviometriaFev) {
		this.pluviometriaFev = pluviometriaFev;
	}

	public Integer getPluviometriaMar() {
		return pluviometriaMar;
	}

	public void setPluviometriaMar(Integer pluviometriaMar) {
		this.pluviometriaMar = pluviometriaMar;
	}

	public Integer getPluviometriaAbr() {
		return pluviometriaAbr;
	}

	public void setPluviometriaAbr(Integer pluviometriaAbr) {
		this.pluviometriaAbr = pluviometriaAbr;
	}

	public Integer getPluviometriaMai() {
		return pluviometriaMai;
	}

	public void setPluviometriaMai(Integer pluviometriaMai) {
		this.pluviometriaMai = pluviometriaMai;
	}

	public Integer getPluviometriaJun() {
		return pluviometriaJun;
	}

	public void setPluviometriaJun(Integer pluviometriaJun) {
		this.pluviometriaJun = pluviometriaJun;
	}

	public Integer getPluviometriaJul() {
		return pluviometriaJul;
	}

	public void setPluviometriaJul(Integer pluviometriaJul) {
		this.pluviometriaJul = pluviometriaJul;
	}

	public Integer getPluviometriaAgo() {
		return pluviometriaAgo;
	}

	public void setPluviometriaAgo(Integer pluviometriaAgo) {
		this.pluviometriaAgo = pluviometriaAgo;
	}

	public Integer getPluviometriaSet() {
		return pluviometriaSet;
	}

	public void setPluviometriaSet(Integer pluviometriaSet) {
		this.pluviometriaSet = pluviometriaSet;
	}

	public Integer getPluviometriaOut() {
		return pluviometriaOut;
	}

	public void setPluviometriaOut(Integer pluviometriaOut) {
		this.pluviometriaOut = pluviometriaOut;
	}

	public Integer getPluviometriaNov() {
		return pluviometriaNov;
	}

	public void setPluviometriaNov(Integer pluviometriaNov) {
		this.pluviometriaNov = pluviometriaNov;
	}

	public Integer getPluviometriaDez() {
		return pluviometriaDez;
	}

	public void setPluviometriaDez(Integer pluviometriaDez) {
		this.pluviometriaDez = pluviometriaDez;
	}

	public Integer getPluviometriaAcumulado() {
		return pluviometriaAcumulado;
	}

	public void setPluviometriaAcumulado(Integer pluviometriaAcumulado) {
		this.pluviometriaAcumulado = pluviometriaAcumulado;
	}

	public FazendaStatus getStatus() {
		return status;
	}

	public void setStatus(FazendaStatus status) {
		this.status = status;
	}

	public List<Retiro> getRetiros() {
		return retiros;
	}

	public void setRetiros(List<Retiro> retiros) {
		this.retiros = retiros;
	}

	public List<CategoriaAnimal> getCategoriasDeAnimais() {
		return categoriasDeAnimais;
	}

	public void setCategoriasDeAnimais(List<CategoriaAnimal> categoriasDeAnimais) {
		this.categoriasDeAnimais = categoriasDeAnimais;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FazendaVH other = (FazendaVH) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FazendaVH [id=" + id + ", nome=" + nome + ", proprietario=" + proprietario + ", areaTotal=" + areaTotal
				+ ", telefone=" + telefone + ", municipio=" + municipio + ", uf=" + uf + ", status=" + status + "]";
	}

	public static FazendaVH from(Fazenda fazenda) {
		
		FazendaVH f = new FazendaVH();
		
		f.id = fazenda.getId();
		f.nome = fazenda.getNome();
		f.areaTotal = fazenda.getAreaTotal();
		f.entreSafra = fazenda.getEntreSafra();
		f.pluviometriaJan = fazenda.getPluviometriaJan();
		f.pluviometriaFev = fazenda.getPluviometriaFev();
		f.pluviometriaMar = fazenda.getPluviometriaMar();
		f.pluviometriaAbr = fazenda.getPluviometriaAbr();
		f.pluviometriaMai = fazenda.getPluviometriaMai();
		f.pluviometriaJun = fazenda.getPluviometriaJun();
		f.pluviometriaJul = fazenda.getPluviometriaJul();
		f.pluviometriaAgo = fazenda.getPluviometriaAgo();
		f.pluviometriaSet = fazenda.getPluviometriaSet();
		f.pluviometriaOut = fazenda.getPluviometriaOut();
		f.pluviometriaNov = fazenda.getPluviometriaNov();
		f.pluviometriaDez = fazenda.getPluviometriaDez();
		f.pluviometriaAcumulado = fazenda.getPluviometriaAcumulado();
		f.safra = fazenda.getSafra();
		f.status = fazenda.getStatus();
		f.telefone = fazenda.getTelefone();
		f.municipio = fazenda.getMunicipio();
		f.uf = fazenda.getUf();
		
		return f;
	}
	
	
	
}