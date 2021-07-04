package ilion.gc.categoria.negocio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import ilion.util.Uteis;

@Embeddable
public class ArtigoConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="artigo_ordem",length=30)
	private String ordem;
	
	@Column(name="artigo_conteudos")
	private String conteudos;
	
	@Column(name="artigo_secoes")
	private String secoes;
	
	@Column(name="artigo_sitemap")
	private Boolean possuiSitemap;
	
	@Column(name="artigo_possui_artigo_menu")
	private Boolean possuiArtigoMenu;
	
	@Column(name="artigo_possui_sub_titulo")
	private Boolean possuiSubTitulo;

	@Column(name="artigo_possui_telefone")
	private Boolean possuiTelefone;
	
	@Column(name="artigo_possui_email")
	private Boolean possuiEmail;
	
	@Column(name="artigo_possui_preco")
	private Boolean possuiPreco;
	
	@Column(name="artigo_possui_link")
	private Boolean possuiLink;
	
	@Column(name="artigo_possui_arquivos")
	private Boolean possuiArquivos;
	
	@Column(name="artigo_possui_video")
	private Boolean possuiVideo;
	
	@Column(name="artigo_possui_rss")
	private Boolean possuiRSS;
	
	@Column(name="artigo_possui_palavras_chave")
	private Boolean possuiPalavrasChave;
	
	@Column(name="artigo_possui_descricao")
	private Boolean possuiDescricao;
	
	@Column(name="artigo_possui_data_evento")
	private Boolean possuiDataEvento;
	
	@Column(name="artigo_possui_coordenadas")
	private Boolean possuiCoordendas;
	
	@Column(name="artigo_possui_previsao")
	private Boolean possuiPrevisao;
	
	@Column(name="artigo_possui_cidade")
	private Boolean possuiCidade;
	
	public Boolean getPossuiTelefone() {
		return possuiTelefone;
	}

	public void setPossuiTelefone(Boolean possuiTelefone) {
		this.possuiTelefone = possuiTelefone;
	}

	public Boolean getPossuiEmail() {
		return possuiEmail;
	}

	public void setPossuiEmail(Boolean possuiEmail) {
		this.possuiEmail = possuiEmail;
	}

	public String getConteudos() {
		return conteudos;
	}

	public void setConteudos(String conteudos) {
		this.conteudos = conteudos;
	}

	public Boolean getPossuiSubTitulo() {
		return possuiSubTitulo;
	}

	public void setPossuiSubTitulo(Boolean possuiSubTitulo) {
		this.possuiSubTitulo = possuiSubTitulo;
	}

	public String getSecoes() {
		return secoes;
	}

	public void setSecoes(String secoes) {
		this.secoes = secoes;
	}
	
	public Boolean getPossuiSitemap() {
		return possuiSitemap;
	}

	public void setPossuiSitemap(Boolean possuiSitemap) {
		this.possuiSitemap = possuiSitemap;
	}

	public Boolean getPossuiLink() {
		return possuiLink;
	}

	public void setPossuiLink(Boolean possuiLink) {
		this.possuiLink = possuiLink;
	}

	public Boolean getPossuiArtigoMenu() {
		return possuiArtigoMenu;
	}

	public void setPossuiArtigoMenu(Boolean possuiArtigoMenu) {
		this.possuiArtigoMenu = possuiArtigoMenu;
	}

	public Boolean getPossuiArquivos() {
		return possuiArquivos;
	}

	public void setPossuiArquivos(Boolean possuiArquivos) {
		this.possuiArquivos = possuiArquivos;
	}

	public Boolean getPossuiRSS() {
		return possuiRSS;
	}

	public void setPossuiRSS(Boolean possuiRSS) {
		this.possuiRSS = possuiRSS;
	}

	public Boolean getPossuiVideo() {
		return possuiVideo;
	}

	public void setPossuiVideo(Boolean possuiVideo) {
		this.possuiVideo = possuiVideo;
	}

	public Boolean getPossuiPalavrasChave() {
		return possuiPalavrasChave;
	}

	public void setPossuiPalavrasChave(Boolean possuiPalavrasChave) {
		this.possuiPalavrasChave = possuiPalavrasChave;
	}

	public Boolean getPossuiDescricao() {
		return possuiDescricao;
	}

	public void setPossuiDescricao(Boolean possuiDescricao) {
		this.possuiDescricao = possuiDescricao;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}
	
	public Boolean getPossuiDataEvento() {
		return possuiDataEvento;
	}

	public void setPossuiDataEvento(Boolean possuiDataEvento) {
		this.possuiDataEvento = possuiDataEvento;
	}
	
	
	public Boolean getPossuiCoordendas() {

		return possuiCoordendas;
	}
	
	
	public void setPossuiCoordendas( Boolean possuiCoordendas ) {

		this.possuiCoordendas = possuiCoordendas;
	}

	public Boolean getPossuiPreco() {
		return possuiPreco;
	}

	public void setPossuiPreco(Boolean possuiPreco) {
		this.possuiPreco = possuiPreco;
	}

	public Boolean getPossuiPrevisao() {
		return possuiPrevisao;
	}

	public void setPossuiPrevisao(Boolean possuiPrevisao) {
		this.possuiPrevisao = possuiPrevisao;
	}

	public Boolean getPossuiCidade() {
		return possuiCidade;
	}

	public void setPossuiCidade(Boolean possuiCidade) {
		this.possuiCidade = possuiCidade;
	}


	@Transient
	List<String> secoesLista = null;
	
	public List<String> getSecoesLista(){
		if( secoesLista == null ) {
			secoesLista = Uteis.formarListaComString(secoes, ";");
		}
		
		return secoesLista;
	}
	
	@Transient
	List<String> conteudosLista = null;
	
	public List<String> getConteudosLista(){
		if( conteudosLista == null ) {
			conteudosLista = Uteis.formarListaComString(conteudos, ";");
		}
		
		return conteudosLista;
	}
}