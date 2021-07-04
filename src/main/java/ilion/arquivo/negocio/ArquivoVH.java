package ilion.arquivo.negocio;

public class ArquivoVH {

	private String url;
	
	private String nome;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public static ArquivoVH from(String nome, String url) {
		
		ArquivoVH arquivoVH = new ArquivoVH();
		
		arquivoVH.nome = nome;
		arquivoVH.url = url;
		
		return arquivoVH;
	}
	
	
}