package ilion.terrafos.cadastros.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="terrafos_plano_de_acao")
public class PlanoDeAcao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_plano_de_acao_id_seq")
	@SequenceGenerator(name="terrafos_plano_de_acao_id_seq", sequenceName="terrafos_plano_de_acao_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fazenda fazenda;
	
	@Column
	private Date dataVisita = new Date();
	
	@Column(nullable = false, columnDefinition = "text")
	private String oQue;
	
	@Column(columnDefinition = "text")
	private String como;

	@Column(columnDefinition = "text")
	private String porQue;
	
	@Column(columnDefinition = "text")
	private String quem;
	
	@Column(columnDefinition = "text")
	private String quando;
	
	@Enumerated(EnumType.STRING)
	private PlanoDeAcaoStatus status;
	
	@Column
	private String custo;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fazenda getFazenda() {
		return fazenda;
	}

	public void setFazenda(Fazenda fazenda) {
		this.fazenda = fazenda;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public String getoQue() {
		return oQue;
	}

	public void setoQue(String oQue) {
		this.oQue = oQue;
	}

	public String getComo() {
		return como;
	}

	public void setComo(String como) {
		this.como = como;
	}

	public String getPorQue() {
		return porQue;
	}

	public void setPorQue(String porQue) {
		this.porQue = porQue;
	}

	public String getQuem() {
		return quem;
	}

	public void setQuem(String quem) {
		this.quem = quem;
	}

	public String getQuando() {
		return quando;
	}

	public void setQuando(String quando) {
		this.quando = quando;
	}

	public PlanoDeAcaoStatus getStatus() {
		return status;
	}

	public void setStatus(PlanoDeAcaoStatus status) {
		this.status = status;
	}

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
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
		PlanoDeAcao other = (PlanoDeAcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanoDeAcao [id=" + id + ", fazenda=" + fazenda + ", dataVisita=" + dataVisita + ", oQue=" + oQue
				+ ", como=" + como + ", porQue=" + porQue + ", quem=" + quem + ", quando=" + quando + ", status="
				+ status + ", custo=" + custo + "]";
	}

	
}
