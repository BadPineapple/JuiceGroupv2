package ilion.gc.categoria.negocio;

import java.util.ArrayList;
import java.util.List;

public class GrupoVH {
	
	private String nome;
	
	private List<CategoriaArtigo> categorias = new ArrayList<CategoriaArtigo>();

	public GrupoVH(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CategoriaArtigo> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaArtigo> categorias) {
		this.categorias = categorias;
	}
	
	public static List<GrupoVH> from(List<CategoriaArtigo> categoriaArtigos, String site) {
		
		List<GrupoVH> grupos = new ArrayList<GrupoVH>();
		
		if( categoriaArtigos == null || categoriaArtigos.isEmpty() ) {
			return grupos;
		}
		
		for (CategoriaArtigo categoriaArtigo : categoriaArtigos) {
			
			if( ! categoriaArtigo.getSite().equals(site) ) {
				continue;
			}
			
			GrupoVH grupoVH = new GrupoVH(categoriaArtigo.getGrupo());
			
			Boolean grupoExistente = 
					grupos.contains(new GrupoVH(categoriaArtigo.getGrupo()));
			
			if( grupoExistente ) {
				int index = grupos.indexOf(grupoVH);
				grupoVH = grupos.get(index);
			} else {
				grupos.add(grupoVH); 
			}
			
			grupoVH.getCategorias().add(categoriaArtigo);
			
		}
		
		return grupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		GrupoVH other = (GrupoVH) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}