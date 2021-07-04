package ilion.admin.negocio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import ilion.gc.categoria.negocio.CategoriaArtigo;

@Entity
@Table(name="adminperfil")
@Proxy(lazy=false)
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=100, nullable=false)
	private String nome;
	
	@Column
	private Boolean acessoAoSistema;
	
	@Column(length=4096)
	private String permissoesJson;
	
	private String emagisLinkInicial;
	
	@Transient
	private List<String> permissoes;
	
	@ManyToMany
    @JoinTable(name="adminperfil_x_categorias", joinColumns=
    {@JoinColumn(name="perfil_id")}, inverseJoinColumns=
      {@JoinColumn(name="categoria_id")})
    private List<CategoriaArtigo> categorias;
	
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

	public Boolean getAcessoAoSistema() {
		return acessoAoSistema;
	}

	public void setAcessoAoSistema(Boolean acessoAoSistema) {
		this.acessoAoSistema = acessoAoSistema;
	}

	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	public List<CategoriaArtigo> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaArtigo> categorias) {
		this.categorias = categorias;
	}

	public String getPermissoesJson() {
		return permissoesJson;
	}

	public void setPermissoesJson(String permissoesJson) {
		this.permissoesJson = permissoesJson;
	}

	public String getEmagisLinkInicial() {
		return emagisLinkInicial;
	}

	public void setEmagisLinkInicial(String emagisLinkInicial) {
		this.emagisLinkInicial = emagisLinkInicial;
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
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + "]";
	}
	
	
}