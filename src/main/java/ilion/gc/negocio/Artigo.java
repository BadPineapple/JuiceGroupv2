package ilion.gc.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.topico.negocio.Topico;


@Entity
@Table(name="gcartigo")
public class Artigo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="gc_artigo_id_seq")
	@SequenceGenerator(name="gc_artigo_id_seq", sequenceName="gc_artigo_id_seq", allocationSize=1)
	private Long id;

	@ManyToOne
	private CategoriaArtigo categoriaArtigo;

	@ManyToOne
	private SubCategoriaArtigo subCategoria;

	@Column(length=30)
	private String tema;

	@Column(length=255, nullable=false)
	private String titulo;
	
	@Column(length=255)
	private String subTitulo;
	
	@Column(length=255, nullable=false)
	private String enderecoUrl;
	
	@Column(length=100)
	private String telefone;
	
	@Column(length=100)
	private String email;
	
	@Column(length=100)
	private String preco;
	
	@Enumerated(EnumType.STRING)
	private ArtigoMenuEnum artigoMenuEnum;
	
	private String link;
	private String linkTarget;

	@Column(length=1000)
	private String secoes;

	private Date dataAlteracao;
	private Date dataPublicacao;
	private Date dataExpiracao;

	@Column(length=30)
	private String status;
	private Integer posicao;

	@Column
	private Boolean exibeComentarios;
	
	@Column
	private Boolean autoPublicar;

	@Column(length=1024)
	private String youtube;

	@Column(length=512)
	private String conteudoInfo;
	private String palavrasChave;
	private String descricao;


	@Column(length=50)
	private String publicadoPor;

	@Column(length=50)
	private String alteradoPor;
	private Date dataCriacao;

	@Column(length=50)
	private String criadoPor;
	
	private Boolean destaque;
	private Boolean destaque2;
	private Boolean subDestaque;
	
	private Date dataEvento;
	
	private Boolean possuiVideo;
	
	private Integer qtdVis;
	
	private Long clicks;
	
	@ManyToMany
    @JoinTable(name="gcartigo_x_topico", joinColumns=
    {@JoinColumn(name="artigo_id")}, inverseJoinColumns=
      {@JoinColumn(name="topico_id")})
    private List<Topico> topicos;
	
	@Column(length=50)
	private String latitude;
	
	@Column(length=50)
	private String longitude;
	
	@Column(length=50)
	private String previsao;
	
	@Column(length=100)
	private String cidade;
		
	//n�o persistentes
	
	@Transient
	private Date horaPublicacao;
	@Transient
	private Long idSubCategoria;
	@Transient
	private String codControle;
	@Transient
	private String codControleConteudo;
	@Transient
	private String alinhamentoArquivoLateral;
	@Transient
	private List<ArtigoConteudo> artigoConteudos;
	@Transient
	private ArtigoConteudo artigoConteudo;
	@Transient
	private List<String> secoesList;
	
	@Transient
	private Integer horaPublicacaoInt;

	public Artigo() {
		super();
	}

	public Artigo(Long id) {
		this();
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriaArtigo getCategoriaArtigo() {
		return categoriaArtigo;
	}

	public void setCategoriaArtigo(CategoriaArtigo categoriaArtigo) {
		this.categoriaArtigo = categoriaArtigo;
	}

	public SubCategoriaArtigo getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoriaArtigo subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArtigoMenuEnum getArtigoMenuEnum() {
		return artigoMenuEnum;
	}

	public void setArtigoMenuEnum(ArtigoMenuEnum artigoMenuEnum) {
		this.artigoMenuEnum = artigoMenuEnum;
	}

	public String getEnderecoUrl() {
		return enderecoUrl;
	}

	public void setEnderecoUrl(String enderecoUrl) {
		this.enderecoUrl = enderecoUrl;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkTarget() {
		return linkTarget;
	}

	public void setLinkTarget(String linkTarget) {
		this.linkTarget = linkTarget;
	}

	public String getPublicadoPor() {
		return publicadoPor;
	}

	public void setPublicadoPor(String publicadoPor) {
		this.publicadoPor = publicadoPor;
	}

	public String getAlteradoPor() {
		return alteradoPor;
	}

	public void setAlteradoPor(String alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	public Boolean getExibeComentarios() {
		return exibeComentarios;
	}

	public void setExibeComentarios(Boolean exibeComentarios) {
		this.exibeComentarios = exibeComentarios;
	}

	public Boolean getAutoPublicar() {
		return autoPublicar;
	}

	public void setAutoPublicar(Boolean autoPublicar) {
		this.autoPublicar = autoPublicar;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getConteudoInfo() {
		return conteudoInfo;
	}

	public void setConteudoInfo(String conteudoInfo) {
		this.conteudoInfo = conteudoInfo;
	}

	public String getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public Date getHoraPublicacao() {
		return horaPublicacao;
	}

	public void setHoraPublicacao(Date horaPublicacao) {
		this.horaPublicacao = horaPublicacao;
	}

	public Long getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Long idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public String getCodControle() {
		return codControle;
	}

	public void setCodControle(String codControle) {
		this.codControle = codControle;
	}

	public String getAlinhamentoArquivoLateral() {
		return alinhamentoArquivoLateral;
	}

	public void setAlinhamentoArquivoLateral(String alinhamentoArquivoLateral) {
		this.alinhamentoArquivoLateral = alinhamentoArquivoLateral;
	}

	public List<ArtigoConteudo> getArtigoConteudos() {
		return artigoConteudos;
	}

	public void setArtigoConteudos(List<ArtigoConteudo> artigoConteudos) {
		this.artigoConteudos = artigoConteudos;
	}

	public ArtigoConteudo getArtigoConteudo() {
		return artigoConteudo;
	}

	public void setArtigoConteudo(ArtigoConteudo artigoConteudo) {
		this.artigoConteudo = artigoConteudo;
	}

	public String getCodControleConteudo() {
		return codControleConteudo;
	}

	public void setCodControleConteudo(String codControleConteudo) {
		this.codControleConteudo = codControleConteudo;
	}

	public String getSecoes() {
		return secoes;
	}

	public void setSecoes(String secoes) {
		this.secoes = secoes;
	}

	public List<String> getSecoesList() {
		return secoesList;
	}

	public void setSecoesList(List<String> secoesList) {
		this.secoesList = secoesList;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSecoesFormatadas() {
		if(secoes == null) {
			return "";
		}

		String secoesFormatadas = secoes.replace("</s><s>", ", ");
		secoesFormatadas = secoesFormatadas.replace("<s>", "(");
		secoesFormatadas = secoesFormatadas.replace("</s>", ")");

		return secoesFormatadas;
	}

	public Integer getHoraPublicacaoInt() {
		return horaPublicacaoInt;
	}

	public void setHoraPublicacaoInt(Integer horaPublicacaoInt) {
		this.horaPublicacaoInt = horaPublicacaoInt;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getDestaque() {
		return destaque;
	}

	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}

	public Boolean getDestaque2() {
		return destaque2;
	}

	public void setDestaque2(Boolean destaque2) {
		this.destaque2 = destaque2;
	}

	public Boolean getSubDestaque() {
		return subDestaque;
	}

	public void setSubDestaque(Boolean subDestaque) {
		this.subDestaque = subDestaque;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Boolean getPossuiVideo() {
		return possuiVideo;
	}

	public void setPossuiVideo(Boolean possuiVideo) {
		this.possuiVideo = possuiVideo;
	}

	public Integer getQtdVis() {
		return qtdVis;
	}

	public void setQtdVis(Integer qtdVis) {
		this.qtdVis = qtdVis;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}
	
	
	public String getLatitude() {

		return latitude;
	}
	
	
	public void setLatitude( String latitude ) {

		this.latitude = latitude;
	}
	
	
	public String getLongitude() {

		return longitude;
	}
	
	
	public void setLongitude( String longitude ) {

		this.longitude = longitude;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getPrevisao() {
		return previsao;
	}

	public void setPrevisao(String previsao) {
		this.previsao = previsao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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
		Artigo other = (Artigo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artigo [id=" + id + ", categoriaArtigo=" + categoriaArtigo + ", subCategoria=" + subCategoria + ", titulo=" + titulo + ", enderecoUrl=" + enderecoUrl + "]";
	}


	
}