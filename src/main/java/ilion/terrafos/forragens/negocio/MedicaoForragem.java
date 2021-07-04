package ilion.terrafos.forragens.negocio;

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

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Forrageira;
import ilion.terrafos.cadastros.negocio.Pasto;

@Entity
@Table(name="terrafos_medicao_forragem")
public class MedicaoForragem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_medicao_forragem_id_seq")
	@SequenceGenerator(name="terrafos_medicao_forragem_id_seq", sequenceName="terrafos_medicao_forragem_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pasto pasto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Forrageira forrageira;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MedicaoForragemTipo tipo;
	
	@Column
	private Integer altura;
	
	@Column(nullable = false)
	private Date dataCriacao = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pasto getPasto() {
		return pasto;
	}

	public void setPasto(Pasto pasto) {
		this.pasto = pasto;
	}

	public Forrageira getForrageira() {
		return forrageira;
	}

	public void setForrageira(Forrageira forrageira) {
		this.forrageira = forrageira;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public MedicaoForragemTipo getTipo() {
		return tipo;
	}

	public void setTipo(MedicaoForragemTipo tipo) {
		this.tipo = tipo;
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
		MedicaoForragem other = (MedicaoForragem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedicaoForragem [id=" + id + ", pasto=" + pasto + ", forrageira=" + forrageira + ", usuario=" + usuario
				+ ", altura=" + altura + ", dataCriacao=" + dataCriacao + "]";
	}
	
}
