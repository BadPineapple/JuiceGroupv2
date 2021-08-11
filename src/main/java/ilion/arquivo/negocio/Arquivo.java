package ilion.arquivo.negocio;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.util.Uteis;

/**
 * @author thor
 *
 */
@Entity
@Table(name="arquivo")
public class Arquivo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String IMAGEM = "imagem-pequena";
	public static final String IMAGEM_AMPLIADA = "imagem-com-ampliada";
	public static final String IMAGEM_LINK = "imagem-link";
	public static final String DOWNLOAD = "download";
	public static final String FLASH = "flash";
	public static final String VIDEO = "video";
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;
	
	@Column(length=30)
	private String nomeClasse;
	
	private String idObjeto;
	
	private String arquivo1;
	private String arquivo2;
	private String texto;
	private Integer largura;
	private Integer altura;
	private Integer alturaExpandido;
	
	@Column(length=20, nullable=false)
	private String layout;
	
	@Column(length=100)
	private String creditos;
	
	@Column(length=30)
	private String data;
	private Integer posicao;
	
	@Column
	private Boolean naoPublicado;
	
	private String link;
	
	@Column(length=10)
	private String tipoLink;
	
	private String title;
	
	//1-imagem pequena sem imagem grande
	//2-imagem pequena com imagem grande
	//3-download
	//4-flash
	//5-imagem com link
	//6-marca d'agua
	//7-video
	@Column(nullable=false)
	private Byte tipo;
	
	private String codigo;
	
	@Transient
	private String url;
	
	//n�o persistentes
	@Transient
	private Boolean imagemGrande;
	@Transient
	private Boolean tamanhoOriginal;
	@Transient
	private Boolean tamanhoOriginalGrande;
	@Transient
	private Integer larguraPequena;
	@Transient
	private Integer larguraGrande;
	@Transient
	private String opcao;
	@Transient
	private String marcaDAguaPequena;
	@Transient
	private String marcaDAguaGrande;
	@Transient
	private Integer marcaDAguaPequenaX;
	@Transient
	private Integer marcaDAguaPequenaY;
	@Transient
	private Integer marcaDAguaGrandeX;
	@Transient
	private Integer marcaDAguaGrandeY;
	@Transient
	private Float alphaPequena;
	@Transient
	private Float alphaGrande;
	@Transient
	private Boolean arquivosEmMassa;
	
	@Transient
	private MultipartFile arquivo;
	
	@Transient
	private String preview;
	
	@Transient
	private String titulo;// usado pelo artigo quando banner
	
	private String nomeArquivoOriginal;
	
	private String diretorio;
	
	public Arquivo() {
		super();
	}
	
	public Arquivo(String id) {
		super();
		setId(id);
	}
	
	
	public String getId() {
		if (id == null) {
			id = "";
		}
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getArquivo1() {
		if (arquivo1 == null) {
			arquivo1 = "";
		}
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
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
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
	public Integer getAlturaExpandido() {
		return alturaExpandido;
	}
	public void setAlturaExpandido(Integer alturaExpandido) {
		this.alturaExpandido = alturaExpandido;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getCreditos() {
		return creditos;
	}
	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	public Boolean getNaoPublicado() {
		return naoPublicado;
	}
	public void setNaoPublicado(Boolean naoPublicado) {
		this.naoPublicado = naoPublicado;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTipoLink() {
		return tipoLink;
	}
	public void setTipoLink(String tipoLink) {
		this.tipoLink = tipoLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Byte getTipo() {
		return tipo;
	}

	public void setTipo(Byte tipo) {
		this.tipo = tipo;
	}

	public Boolean getImagemGrande() {
		return imagemGrande;
	}
	public void setImagemGrande(Boolean imagemGrande) {
		this.imagemGrande = imagemGrande;
	}
	public Boolean getTamanhoOriginal() {
		return tamanhoOriginal;
	}
	public void setTamanhoOriginal(Boolean tamanhoOriginal) {
		this.tamanhoOriginal = tamanhoOriginal;
	}
	public Boolean getTamanhoOriginalGrande() {
		return tamanhoOriginalGrande;
	}
	public void setTamanhoOriginalGrande(Boolean tamanhoOriginalGrande) {
		this.tamanhoOriginalGrande = tamanhoOriginalGrande;
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
	public String getOpcao() {
		return opcao;
	}
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
	public String getMarcaDAguaPequena() {
		return marcaDAguaPequena;
	}
	public void setMarcaDAguaPequena(String marcaDAguaPequena) {
		this.marcaDAguaPequena = marcaDAguaPequena;
	}
	public String getMarcaDAguaGrande() {
		return marcaDAguaGrande;
	}
	public void setMarcaDAguaGrande(String marcaDAguaGrande) {
		this.marcaDAguaGrande = marcaDAguaGrande;
	}
	public Integer getMarcaDAguaPequenaX() {
		return marcaDAguaPequenaX;
	}
	public void setMarcaDAguaPequenaX(Integer marcaDAguaPequenaX) {
		this.marcaDAguaPequenaX = marcaDAguaPequenaX;
	}
	public Integer getMarcaDAguaPequenaY() {
		return marcaDAguaPequenaY;
	}
	public void setMarcaDAguaPequenaY(Integer marcaDAguaPequenaY) {
		this.marcaDAguaPequenaY = marcaDAguaPequenaY;
	}
	public Integer getMarcaDAguaGrandeX() {
		return marcaDAguaGrandeX;
	}
	public void setMarcaDAguaGrandeX(Integer marcaDAguaGrandeX) {
		this.marcaDAguaGrandeX = marcaDAguaGrandeX;
	}
	public Integer getMarcaDAguaGrandeY() {
		return marcaDAguaGrandeY;
	}
	public void setMarcaDAguaGrandeY(Integer marcaDAguaGrandeY) {
		this.marcaDAguaGrandeY = marcaDAguaGrandeY;
	}
	public Float getAlphaPequena() {
		return alphaPequena;
	}
	public void setAlphaPequena(Float alphaPequena) {
		this.alphaPequena = alphaPequena;
	}
	public Float getAlphaGrande() {
		return alphaGrande;
	}
	public void setAlphaGrande(Float alphaGrande) {
		this.alphaGrande = alphaGrande;
	}
	public Boolean getArquivosEmMassa() {
		return arquivosEmMassa;
	}
	public void setArquivosEmMassa(Boolean arquivosEmMassa) {
		this.arquivosEmMassa = arquivosEmMassa;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}

	public MultipartFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getUrl() {
		
		if( ! Uteis.ehNuloOuVazio(url) ) {
			return url;
		}
		
		if(tipo == null) {
			return "tipo nao definido";
		}
		
		ArquivoUteis arquivoUteis = SpringApplicationContext.getBean(ArquivoUteis.class);
		
		String pathArquivos = arquivoUteis.getEnderecoArquivos();
		
		if( tipo.equals(new Byte("3")) || 
				tipo.equals(new Byte("4")) || 
				tipo.equals(new Byte("7"))) {
			url = pathArquivos+"arquivos/downloads/"+arquivo1;
		} else {
			url = pathArquivos+arquivo1;
		}
		
		return url;
	}
	
	public String getUrl2() {
		
		if( Uteis.ehNuloOuVazio(arquivo2) ) {
			return getUrl();
		}
		
		ArquivoUteis arquivoUteis = SpringApplicationContext.getBean(ArquivoUteis.class);
		
		String pathArquivos = arquivoUteis.getEnderecoArquivos();
		
		String url2 = pathArquivos+arquivo2;
		
		return url2;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getSomenteNomeArquivo() {
		String extensao = ArquivoTipoEnum.getExtensao(arquivo1);
		
		return arquivo1.substring(0, arquivo1.indexOf("."+extensao));
	}
	
	public String getExtensao() {
		return ArquivoTipoEnum.getExtensao(arquivo1);
	}
	
	public static Arquivo fromMap(Map<String, Object> map) {
		
		Arquivo a = new Arquivo();
		
		a.setNomeClasse((String)map.get("nomeclasse"));
		a.setIdObjeto((String)map.get("idobjeto"));
		a.setLayout((String)map.get("layout"));
		
		a.setAltura( getInteger( (Number) map.get("altura") ) );
		a.setArquivo1((String)map.get("arquivo1"));
		a.setArquivo2((String)map.get("arquivo2"));
		a.setCreditos((String)map.get("creditos"));
		a.setData((String)map.get("data"));
		a.setLink((String)map.get("link"));
		a.setTipoLink((String)map.get("tipolink"));
		
		if( Uteis.ehNuloOuVazio(a.getLink()) ) {
			a.setLink((String)map.get("artigolink"));
		}
		
		if( Uteis.ehNuloOuVazio(a.getTipoLink()) ) {
			a.setTipoLink((String)map.get("artigolinktarget"));
		}
		
		a.setTexto((String)map.get("texto"));
		a.setTipo( getByte( (Number) map.get("tipo") ));
		a.setTitle((String)map.get("title"));
		a.setTitulo((String)map.get("titulo"));
		
		return a;
	}
	
	public static Integer getInteger(Number n) {
		if( n == null ) {
			return null;
		}
		
		return n.intValue();
	}
	
	public static Byte getByte(Number n) {
		if( n == null ) {
			return null;
		}
		
		return n.byteValue();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getNomeArquivoOriginal() {
		if (nomeArquivoOriginal == null) {
			nomeArquivoOriginal = "";
		}
		return nomeArquivoOriginal;
	}

	public void setNomeArquivoOriginal(String nomeArquivoOriginal) {
		this.nomeArquivoOriginal = nomeArquivoOriginal;
	}

	public String getDiretorio() {
		if (diretorio == null) {
			diretorio = "";
		}
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	
	public String getImagemApresentar() {
		if (getLink() != null && !getLink().equals("")) {
			return getLink();
		}
		
		return "../assets/images/perfil.png";
	}
	
}