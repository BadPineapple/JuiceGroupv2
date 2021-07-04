/*
 * Created on Feb 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilion.gc.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="gcenquete")
public class Enquete implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String origem;
	private String assunto;
	
	private String opcao1;
	private String opcao2;
	private String opcao3;
	private String opcao4;
	private String opcao5;
	private String opcao6;
	private Integer votosOpcao1;
	private Integer votosOpcao2;
	private Integer votosOpcao3;
	private Integer votosOpcao4;
	private Integer votosOpcao5;
	private Integer votosOpcao6;
	
	private String status;
	private Date dataPublicacao;
	private Date dataExpiracao;
	
	private String conteudoInfo;
	
	private String criadoPor;
	private Date dataCriacao;
	
	@Transient
	private String codControle;
	
	public Enquete() {
		super();
	}
	
	public Enquete(Long id) {
		setId(id);
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpcao1() {
		return opcao1;
	}

	public void setOpcao1(String opcao1) {
		this.opcao1 = opcao1;
	}

	public String getOpcao2() {
		return opcao2;
	}

	public void setOpcao2(String opcao2) {
		this.opcao2 = opcao2;
	}

	public String getOpcao3() {
		return opcao3;
	}

	public void setOpcao3(String opcao3) {
		this.opcao3 = opcao3;
	}

	public String getOpcao4() {
		return opcao4;
	}

	public void setOpcao4(String opcao4) {
		this.opcao4 = opcao4;
	}

	public String getOpcao5() {
		return opcao5;
	}

	public void setOpcao5(String opcao5) {
		this.opcao5 = opcao5;
	}

	public String getOpcao6() {
		return opcao6;
	}

	public void setOpcao6(String opcao6) {
		this.opcao6 = opcao6;
	}

	public Integer getVotosOpcao1() {
		return votosOpcao1;
	}

	public void setVotosOpcao1(Integer votosOpcao1) {
		this.votosOpcao1 = votosOpcao1;
	}

	public Integer getVotosOpcao2() {
		return votosOpcao2;
	}

	public void setVotosOpcao2(Integer votosOpcao2) {
		this.votosOpcao2 = votosOpcao2;
	}

	public Integer getVotosOpcao3() {
		return votosOpcao3;
	}

	public void setVotosOpcao3(Integer votosOpcao3) {
		this.votosOpcao3 = votosOpcao3;
	}

	public Integer getVotosOpcao4() {
		return votosOpcao4;
	}

	public void setVotosOpcao4(Integer votosOpcao4) {
		this.votosOpcao4 = votosOpcao4;
	}

	public Integer getVotosOpcao5() {
		return votosOpcao5;
	}

	public void setVotosOpcao5(Integer votosOpcao5) {
		this.votosOpcao5 = votosOpcao5;
	}

	public Integer getVotosOpcao6() {
		return votosOpcao6;
	}

	public void setVotosOpcao6(Integer votosOpcao6) {
		this.votosOpcao6 = votosOpcao6;
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
	
}