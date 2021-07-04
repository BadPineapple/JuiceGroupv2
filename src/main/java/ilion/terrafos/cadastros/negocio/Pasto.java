package ilion.terrafos.cadastros.negocio;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ilion.util.Uteis;

@Entity
@Table(name="terrafos_pasto")
public class Pasto implements Serializable {
	
	static Logger logger = Logger.getLogger(Pasto.class);
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_pasto_id_seq")
	@SequenceGenerator(name="terrafos_pasto_id_seq", sequenceName="terrafos_pasto_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	private Retiro retiro;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private Float areaTotal;
	
	@ManyToOne
	@JsonIgnore
	private Forrageira forrageira;
	
	@Embedded
	@JsonIgnore
	private AvaliacaoDoPasto avaliacaoDoPasto;
	
	@Column(columnDefinition = "text")
	@JsonIgnore
	private String googleMapsJson;
	
	@Transient
	private Integer totalDeAnimais;
	
	@Transient
	private Integer pesoMedio;
	
	@Transient
	private Double lotacaoMedia;
	
	@Transient
	private List<Map<String, Object>> totaisDeAnimais;
	
	
	public Pasto() {
		super();
	}
	
	public Pasto(Long id) {
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

	public Retiro getRetiro() {
		return retiro;
	}

	public void setRetiro(Retiro retiro) {
		this.retiro = retiro;
	}

	public Float getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(Float areaTotal) {
		this.areaTotal = areaTotal;
	}

	public Forrageira getForrageira() {
		return forrageira;
	}

	public void setForrageira(Forrageira forrageira) {
		this.forrageira = forrageira;
	}

	public AvaliacaoDoPasto getAvaliacaoDoPasto() {
		return avaliacaoDoPasto;
	}

	public void setAvaliacaoDoPasto(AvaliacaoDoPasto avaliacaoDoPasto) {
		this.avaliacaoDoPasto = avaliacaoDoPasto;
	}

	public String getGoogleMapsJson() {
		return googleMapsJson;
	}

	public void setGoogleMapsJson(String googleMapsJson) {
		this.googleMapsJson = googleMapsJson;
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

	public Double getLotacaoMedia() {
		return lotacaoMedia;
	}

	public void setLotacaoMedia(Double lotacaoMedia) {
		this.lotacaoMedia = lotacaoMedia;
	}
	
	public List<Map<String, Object>> getTotaisDeAnimais() {
		return totaisDeAnimais;
	}

	public void setTotaisDeAnimais(List<Map<String, Object>> totaisDeAnimais) {
		this.totaisDeAnimais = totaisDeAnimais;
	}

	public Float getAeeHa() {
		
		if( this.areaTotal == null ) {
			return null;
		}
		
		if( this.avaliacaoDoPasto == null || this.avaliacaoDoPasto.getAee() == null ) {
			return null;
		}
		
		try {
			
			Float aeeHa = this.areaTotal * this.avaliacaoDoPasto.getAee().floatValue()/100;
			
			Float __aeeHa = Uteis.convert2MaximumFractionDigits(aeeHa);
			
			return __aeeHa;
			
		} catch (Exception e) {
			return 0f;
		}
		
	}
	
	@JsonIgnore
	public Float getLotacaoMediaJan() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getJan() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getJan());
	}
	@JsonIgnore
	public Float getLotacaoMediaFev() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getFev() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getFev());
	}
	@JsonIgnore
	public Float getLotacaoMediaMar() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getMar() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getMar());
	}
	@JsonIgnore
	public Float getLotacaoMediaAbr() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getAbr() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getAbr());
	}
	@JsonIgnore
	public Float getLotacaoMediaMai() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getMai() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getMai());
	}
	@JsonIgnore
	public Float getLotacaoMediaJun() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getJun() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getJun());
	}
	@JsonIgnore
	public Float getLotacaoMediaJul() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getJul() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getJul());
	}
	@JsonIgnore
	public Float getLotacaoMediaAgo() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getAgo() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getAgo());
	}
	@JsonIgnore
	public Float getLotacaoMediaSet() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getSet() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getSet());
	}
	@JsonIgnore
	public Float getLotacaoMediaOut() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getOut() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getOut());
	}
	@JsonIgnore
	public Float getLotacaoMediaNov() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getNov() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getNov());
	}
	@JsonIgnore
	public Float getLotacaoMediaDez() {
		
		if( this.forrageira == null ) {
			return null;
		}
		
		if( this.forrageira.getDez() == null ) {
			return null;
		}
		
		return calcularLotacaoMedia(this.forrageira.getDez());
	}
	@JsonIgnore
	private Float calcularLotacaoMedia(Integer producaoForragemNoMesEmPorc) {
		
		if( producaoForragemNoMesEmPorc == null ) {
			return null;
		}
		
		if( this.avaliacaoDoPasto.getOf() == null ) {
			return null;
		}
		
		float resultado = 0f;
		
		try {
			
			float producao = this.forrageira.getProducao().floatValue()*(producaoForragemNoMesEmPorc.floatValue()/100);
			
			resultado = producao/30.42f;
			resultado = resultado/this.avaliacaoDoPasto.getOf();
			resultado = resultado/450;
			resultado = resultado*this.getAeeHa();
			resultado = resultado*100;
			
		} catch (Exception e) {
			logger.error("erro ao calcular lotação média: pasto.id: "+this.id, e);
		}
		
		return resultado;
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaJan() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getJan());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaFev() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getFev());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaMar() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getMar());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaAbr() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getAbr());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaMai() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getMai());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaJun() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getJun());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaJul() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getJul());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaAgo() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getAgo());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaSet() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getSet());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaOut() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getOut());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaNov() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getNov());
	}
	@JsonIgnore
	public Float getLotacaoMediaPorAeeHaDez() {
		return calcularLotacaoMediaPorAeeHa(this.forrageira.getDez());
	}
	@JsonIgnore
	private Float calcularLotacaoMediaPorAeeHa(Integer producaoForragem) {
		
		if( producaoForragem == null ) {
			return null;
		}
		
		Float lotacaoMedia = calcularLotacaoMedia(producaoForragem);
		
		if( lotacaoMedia == null ) {
			return null;
		}
		
		try {
			return lotacaoMedia / this.getAeeHa();
		} catch (Exception e) {
			return 0f;
		}
		
		
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
		Pasto other = (Pasto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pasto [id=" + id + ", retiro=" + retiro + ", nome=" + nome + ", areaTotal=" + areaTotal
				+ ", forrageira=" + forrageira + "]";
	}


	
}
