package ilion.contato.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="contatoxgrupo")
public class ContatoXGrupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Contato contato;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private ContatoGrupo grupo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public ContatoGrupo getGrupo() {
		return grupo;
	}
	public void setGrupo(ContatoGrupo grupo) {
		this.grupo = grupo;
	}
}