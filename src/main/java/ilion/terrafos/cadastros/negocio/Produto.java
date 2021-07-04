package ilion.terrafos.cadastros.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="terrafos_produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_produto_id_seq")
	@SequenceGenerator(name="terrafos_produto_id_seq", sequenceName="terrafos_produto_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	private Fazenda fazenda;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String unidade;

	@Column
	private Integer apresentacao;
	
	@Column
	private Integer estoqueInicial;
	
	@Column(nullable = false)
	@JsonIgnore
	private Date data = new Date();
	
	

	public Produto() {
		super();
	}

	public Produto(Long id) {
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

	

	public Fazenda getFazenda() {
		return fazenda;
	}

	public void setFazenda(Fazenda fazenda) {
		this.fazenda = fazenda;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Integer getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(Integer apresentacao) {
		this.apresentacao = apresentacao;
	}

	public Integer getEstoqueInicial() {
		return estoqueInicial;
	}

	public void setEstoqueInicial(Integer estoqueInicial) {
		this.estoqueInicial = estoqueInicial;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", fazenda=" + fazenda + ", nome=" + nome + ", unidade=" + unidade
				+ ", apresentacao=" + apresentacao + ", estoqueInicial=" + estoqueInicial + ", data=" + data + "]";
	}

	
	
}
