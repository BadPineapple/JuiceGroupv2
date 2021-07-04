package ilion.terrafos.cadastros.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="terrafos_fazenda")
public class Fazenda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_fazenda_id_seq")
	@SequenceGenerator(name="terrafos_fazenda_id_seq", sequenceName="terrafos_fazenda_id_seq", allocationSize=1)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String proprietario;
	
	@Column
	private Float areaTotal;
	
	@Column
	private String telefone;
	
	@Column(nullable = false)
	private String municipio;
	
	@Column(length=2,nullable = false)
	private String uf;
	
	@Column
	private Float safra;
	
	@Column
	private Float entreSafra;
	
	@Column
	private Integer pluviometriaJan;
	
	@Column
	private Integer pluviometriaFev;
	
	@Column
	private Integer pluviometriaMar;
	
	@Column
	private Integer pluviometriaAbr;
	
	@Column
	private Integer pluviometriaMai;
	
	@Column
	private Integer pluviometriaJun;
	
	@Column
	private Integer pluviometriaJul;
	
	@Column
	private Integer pluviometriaAgo;
	
	@Column
	private Integer pluviometriaSet;
	
	@Column
	private Integer pluviometriaOut;
	
	@Column
	private Integer pluviometriaNov;
	
	@Column
	private Integer pluviometriaDez;
	
	@Column
	private Integer pluviometriaAcumulado;
	
	@Enumerated(EnumType.STRING)
	private FazendaStatus status;
	
	@Column(columnDefinition = "text")
	@JsonIgnore
	private String googleMapsJson;
	
	public Fazenda() {
		super();
	}

	public Fazenda(Long id) {
		super();
		this.id = id;
	}



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

	public String getGoogleMapsJson() {
		return googleMapsJson;
	}

	public void setGoogleMapsJson(String googleMapsJson) {
		this.googleMapsJson = googleMapsJson;
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
		Fazenda other = (Fazenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fazenda [id=" + id + ", nome=" + nome + ", proprietario=" + proprietario + ", municipio=" + municipio
				+ ", uf=" + uf + ", status=" + status + "]";
	}
	
	
}
