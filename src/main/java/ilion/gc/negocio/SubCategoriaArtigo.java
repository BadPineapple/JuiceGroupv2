package ilion.gc.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ilion.gc.categoria.negocio.CategoriaArtigo;

@Entity
@Table(name="gcsubcategoriaartigo")
public class SubCategoriaArtigo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="gc_subcategoria_id_seq")
	@SequenceGenerator(name="gc_subcategoria_id_seq", sequenceName="gc_subcategoria_id_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private CategoriaArtigo categoriaArtigo;
	
	@Column(length=255, nullable=false)
	private String nome;
	
	@Column(length=255, nullable=false)
	private String enderecoUrl;
	
	@Column(length=4096)
	private String descricao;
	
	@Column(length=30)
	private String telefone;
	
	@Column(length=100)
	private String email;
	
	@Column(length=20)
	private String status;
	
	private Date dataPublicacao;
	private Date dataExpiracao;
	
	private Integer posicao;
	
	@Column(length=50)
	private String cadastradoPor;
	private Date dataCriacao;
	
	@Column(length=512)
	private String conteudoInfo;
	
	@Transient
	private String codControle;
	@Transient
	private String alinhamentoArquivosLaterais;

	public SubCategoriaArtigo() {
		super();
	}
	
	public SubCategoriaArtigo(Long id) {
		super();
		setId(id);
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

	public CategoriaArtigo getCategoriaArtigo() {
		return categoriaArtigo;
	}

	public void setCategoriaArtigo(CategoriaArtigo categoriaArtigo) {
		this.categoriaArtigo = categoriaArtigo;
	}

	public String getEnderecoUrl() {
		return enderecoUrl;
	}

	public void setEnderecoUrl(String enderecoUrl) {
		this.enderecoUrl = enderecoUrl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	public String getCadastradoPor() {
		return cadastradoPor;
	}

	public void setCadastradoPor(String cadastradoPor) {
		this.cadastradoPor = cadastradoPor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getConteudoInfo() {
		return conteudoInfo;
	}

	public void setConteudoInfo(String conteudoInfo) {
		this.conteudoInfo = conteudoInfo;
	}

	public String getCodControle() {
		return codControle;
	}

	public void setCodControle(String codControle) {
		this.codControle = codControle;
	}

	public String getAlinhamentoArquivosLaterais() {
		return alinhamentoArquivosLaterais;
	}

	public void setAlinhamentoArquivosLaterais(String alinhamentoArquivosLaterais) {
		this.alinhamentoArquivosLaterais = alinhamentoArquivosLaterais;
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
	
}