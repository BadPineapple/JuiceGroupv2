package ilion.gc.categoria.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ilion.util.StatusEnum;

@Entity
@Table(name="gccategoriartigo")
public class CategoriaArtigo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="gc_categoria_id_seq")
	@SequenceGenerator(name="gc_categoria_id_seq", sequenceName="gc_categoria_id_seq", allocationSize=1)
	private Long id;
	
	@Column(length=30,nullable=false)
	private String site;
	
	@Column(length=255,nullable=false)
	private String nome;
	
	@Column(length=30,nullable=false)
	private String grupo;

	@Embedded
	private ArtigoConfig artigoConfig;

	@Embedded
	private SubCategoriaConfig subCategoriaConfig;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum statusEnum;
	
	public CategoriaArtigo() {
		super();
	}

	public CategoriaArtigo(Long id) {
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public ArtigoConfig getArtigoConfig() {
		return artigoConfig;
	}

	public void setArtigoConfig(ArtigoConfig artigoConfig) {
		this.artigoConfig = artigoConfig;
	}

	public SubCategoriaConfig getSubCategoriaConfig() {
		return subCategoriaConfig;
	}

	public void setSubCategoriaConfig(SubCategoriaConfig subCategoriaConfig) {
		this.subCategoriaConfig = subCategoriaConfig;
	}

	public String getNomeSite() {
		return grupo+" - "+nome+" ("+site+")";
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
		CategoriaArtigo other = (CategoriaArtigo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
	
	
}