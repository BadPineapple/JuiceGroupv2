package ilion.terrafos.animais.negocio;

import java.io.Serializable;
import java.util.Arrays;
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
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.Raca;
import ilion.util.exceptions.ValidacaoException;

@Entity
@Table(name="terrafos_lote")
public class Lote implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_lote_id_seq")
	@SequenceGenerator(name="terrafos_lote_id_seq", sequenceName="terrafos_lote_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pasto pasto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private CategoriaAnimal categoriaAnimal;
	
	@ManyToOne
	private Raca raca;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LoteTipo tipo;
	
	@Column
	private Integer valor;
	
	@Enumerated(EnumType.STRING)
	private MotivoBaixa motivoBaixa;
	
	@Column(nullable = false)
	private Date dataCriacao = new Date();

	
	
	public Lote() {
		super();
	}
	
	public Lote(Long id) {
		super();
		this.id = id;
	}

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

	public CategoriaAnimal getCategoriaAnimal() {
		return categoriaAnimal;
	}

	public void setCategoriaAnimal(CategoriaAnimal categoriaAnimal) {
		this.categoriaAnimal = categoriaAnimal;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LoteTipo getTipo() {
		return tipo;
	}

	public void setTipo(LoteTipo tipo) {
		this.tipo = tipo;
	}

	public MotivoBaixa getMotivoBaixa() {
		return motivoBaixa;
	}

	public void setMotivoBaixa(MotivoBaixa motivoBaixa) {
		this.motivoBaixa = motivoBaixa;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
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
		Lote other = (Lote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lote [id=" + id + ", pasto=" + pasto + ", categoriaAnimal=" + categoriaAnimal + ", usuario=" + usuario
				+ ", tipo=" + tipo + ", valor=" + valor + ", dataCriacao=" + dataCriacao + "]";
	}

	public void ehValido() {
		
		if( this.getPasto() == null || this.getPasto().getId() == null ) {
			throw new ValidacaoException("Pasto deve ser definido.");
		}
		
		if( this.getCategoriaAnimal() == null || this.getCategoriaAnimal().getId() == null ) {
			throw new ValidacaoException("Categoria de animais deve ser definido.");
		}
		
		if( this.getUsuario() == null || this.getUsuario().getId() == null ) {
			throw new ValidacaoException("Usuário deve ser definido.");
		}
		
		if( this.getTipo() == null ) {
			throw new ValidacaoException("Tipo de lote deve ser definido.");
		}
		
		if( this.getValor() == null ) {
			throw new ValidacaoException("Valor deve ser definido.");
		}
		
		if( Arrays.asList( LoteTipo.MORTE, LoteTipo.VENDA, LoteTipo.TRANSFERENCIA_SAIDA ).contains(this.tipo) ) {
			
			if( this.motivoBaixa == null ) {
				throw new ValidacaoException("Motivo da baixa deve ser definido.");
			}
			
		}
		
	}


	
}
