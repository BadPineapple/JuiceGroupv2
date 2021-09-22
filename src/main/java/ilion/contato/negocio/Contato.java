package ilion.contato.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ilion.SpringApplicationContext;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "contato_id_seq")
  @SequenceGenerator(name = "contato_id_seq", sequenceName = "contato_id_seq", allocationSize = 1)
  private Long id;

  private String nome;

  private String empresa;

  private String endereco;

  private String cep;

  private String setor;

  private String cidade;

  private String assunto;

  private String mensagem;

  private String dataNascimentoString;

  private String estado;

  private String pais;

  private String complemento;

  private String email;

  private String telefone;

  private String celular;

  private Boolean permissaoEmail;

  private String obs;

  private String cadastradoPor;

  private Date dataCriacao;

  private String senha;

  private String cpfCnpj;

  @Transient
  private String confirmarSenha;

  @Transient
  private String codigoValidador;

  @Transient
  private List<Long> idsGrupos;

  private Boolean ouvidoria;
  
  public Contato() {
    super();
  }

  public Contato(Long id) {
    super();
    this.id = id;
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

  public String getEmpresa() {
    return empresa;
  }

  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getSetor() {
    return setor;
  }

  public void setSetor(String setor) {
    this.setor = setor;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public Boolean getPermissaoEmail() {
    return permissaoEmail;
  }

  public void setPermissaoEmail(Boolean permissaoEmail) {
    this.permissaoEmail = permissaoEmail;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getConfirmarSenha() {
    return confirmarSenha;
  }

  public void setConfirmarSenha(String confirmarSenha) {
    this.confirmarSenha = confirmarSenha;
  }

  public String getGruposFormatados() {
    ContatoGrupoNegocio contatoGrupoNegocio = SpringApplicationContext.getBean(ContatoGrupoNegocio.class);
    return contatoGrupoNegocio.formatarGrupos(this);
  }

  public List<Long> getIdsGrupos() {
    return idsGrupos;
  }

  public void setIdsGrupos(List<Long> idsGrupos) {
    this.idsGrupos = idsGrupos;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public String getAssunto() {
    return assunto;
  }

  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getCodigoValidador() {
    return codigoValidador;
  }

  public void setCodigoValidador(String codigoValidador) {
    this.codigoValidador = codigoValidador;
  }

  public String getDataNascimentoString() {
    return dataNascimentoString;
  }

  public void setDataNascimentoString(String dataNascimentoString) {
    this.dataNascimentoString = dataNascimentoString;
  }

  public Boolean getOuvidoria() {
	return ouvidoria;
  }
	
  public void setOuvidoria(Boolean ouvidoria) {
	this.ouvidoria = ouvidoria;
  }
}