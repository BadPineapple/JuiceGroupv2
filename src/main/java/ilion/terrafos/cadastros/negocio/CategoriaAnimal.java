package ilion.terrafos.cadastros.negocio;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="terrafos_categoria_animal")
public class CategoriaAnimal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_categoria_animal_id_seq")
	@SequenceGenerator(name="terrafos_categoria_animal_id_seq", sequenceName="terrafos_categoria_animal_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	private Fazenda fazenda;
	
	@Column(nullable = false)
	private String lote;
	
	@Column(nullable = false)
	private String sigla;
	
	@Column(nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Sexo nascimentosSexo;
	
	@Column(columnDefinition = "text")
	private String observacao;
	

	public CategoriaAnimal() {
		super();
	}
	
	public CategoriaAnimal(Long id) {
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

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Sexo getNascimentosSexo() {
		return nascimentosSexo;
	}

	public void setNascimentosSexo(Sexo nascimentosSexo) {
		this.nascimentosSexo = nascimentosSexo;
	}

	@Override
	public String toString() {
		return "CategoriaAnimal [id=" + id + ", fazenda=" + fazenda + ", lote=" + lote + ", sigla=" + sigla + ", nome="
				+ nome + ", observacao=" + observacao + "]";
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
		CategoriaAnimal other = (CategoriaAnimal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
