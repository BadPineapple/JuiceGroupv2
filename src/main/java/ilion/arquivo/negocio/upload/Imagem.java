package ilion.arquivo.negocio.upload;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ilion.arquivo.negocio.Arquivo;

public class Imagem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeClasse;
	private String idObjeto;
	private String codigo;

	private String arquivo1;
	private String arquivo2;

	private Integer largura;
	private Integer altura;

	private Boolean redimensionar; //imagem pequena
	private Integer larguraPequena;

	private Boolean imagemAmpliada; //imagem pequena
	private Integer larguraGrande;

	private Integer posicao;

	private List<MultipartFile> arquivos;

	public String getArquivo1() {
		return arquivo1;
	}

	public void setArquivo1(String arquivo1) {
		this.arquivo1 = arquivo1;
	}

	public String getArquivo2() {
		return arquivo2;
	}

	public void setArquivo2(String arquivo2) {
		this.arquivo2 = arquivo2;
	}

	public Integer getLargura() {
		return largura;
	}

	public void setLargura(Integer largura) {
		this.largura = largura;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}


	public List<MultipartFile> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<MultipartFile> arquivos) {
		this.arquivos = arquivos;
	}

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public String getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getLarguraPequena() {
		return larguraPequena;
	}

	public void setLarguraPequena(Integer larguraPequena) {
		this.larguraPequena = larguraPequena;
	}

	public Integer getLarguraGrande() {
		return larguraGrande;
	}

	public void setLarguraGrande(Integer larguraGrande) {
		this.larguraGrande = larguraGrande;
	}

	public Imagem clone() {
		Imagem i = new Imagem();
		
		i.setAltura(this.altura);
		i.setLargura(this.largura);
		
		i.setNomeClasse(this.nomeClasse);
		i.setIdObjeto(this.idObjeto);
		i.setCodigo(this.codigo);
		
		i.setRedimensionar(this.redimensionar);
		i.setLarguraPequena(this.larguraPequena);
		
		i.setImagemAmpliada(this.imagemAmpliada);
		i.setLarguraGrande(this.larguraGrande);
		
		i.setPosicao(this.posicao);
		
		return i;
	}

	public Arquivo toArquivo() {
		Arquivo a = new Arquivo();

		a.setNomeClasse(this.nomeClasse);
		a.setIdObjeto(this.idObjeto);
		a.setCodigo(this.codigo);

		a.setLargura(this.largura);
		a.setAltura(this.altura);

		a.setArquivo1(this.arquivo1);
		a.setArquivo2(this.arquivo2);
		
		a.setPosicao(this.posicao);
		
		a.setLayout("lateral");
		
		a.setNaoPublicado(false);
		
		return a;
	}

	public Boolean getRedimensionar() {
		return redimensionar;
	}

	public void setRedimensionar(Boolean redimensionar) {
		this.redimensionar = redimensionar;
	}

	public Boolean getImagemAmpliada() {
		return imagemAmpliada;
	}

	public void setImagemAmpliada(Boolean imagemAmpliada) {
		this.imagemAmpliada = imagemAmpliada;
	}
	
	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
}