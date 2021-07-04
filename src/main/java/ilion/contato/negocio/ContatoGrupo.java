package ilion.contato.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="contatogrupo")
public class ContatoGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="contato_grupo_id_seq")
	@SequenceGenerator(name="contato_grupo_id_seq", sequenceName="contato_grupo_id_seq", allocationSize=1)
	private Long id;
	
	@Column(length=100, nullable=false)
	private String nome;
	
	@Transient
	private Integer qtd;
	
	public ContatoGrupo() {
		super();
	}

	public ContatoGrupo(Long id) {
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

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}