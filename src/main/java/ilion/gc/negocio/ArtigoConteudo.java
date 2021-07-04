 /*
 * Created on 19/10/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilion.gc.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="gcartigoconteudo")
public class ArtigoConteudo implements Serializable, Comparable<ArtigoConteudo> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Artigo artigo;
	
	@Column(columnDefinition="text")
	private String texto;
	
	@Column(length=20)
	private String layout;
	private Byte posicao;
	
	@Column(length=512)
	private String conteudoInfo;
	
	@Transient
	private String codControle;
	@Transient
	private String alinhamentoArquivoLateral;
	
	public ArtigoConteudo() {
		super();
	}
	
	public ArtigoConteudo(String id) {
		this();
		setId(id);
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Byte getPosicao() {
		return posicao;
	}

	public void setPosicao(Byte posicao) {
		this.posicao = posicao;
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

	public String getAlinhamentoArquivoLateral() {
		return alinhamentoArquivoLateral;
	}

	public void setAlinhamentoArquivoLateral(String alinhamentoArquivoLateral) {
		this.alinhamentoArquivoLateral = alinhamentoArquivoLateral;
	}

	public int compareTo(ArtigoConteudo o) {
		if(o.getPosicao() == null) {
			return 0;
		}
		
		return this.getPosicao().compareTo(o.getPosicao());
	}
}