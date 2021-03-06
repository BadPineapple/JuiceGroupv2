package ilion.vitazure.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ilion.arquivo.negocio.Arquivo;
import ilion.util.StringUtil;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "pessoa_id_seq")
	  @SequenceGenerator(name = "pessoa_id_seq", sequenceName = "pessoa_id_seq", allocationSize = 1)
	  private Long id;
	  private String nome;
	  private String genero;
	  private String email;
	  private String telefone;
	  private String celular;
	  private String cpf;
	  private String senha;
	  private String cep;
	  private String setor;
	  private String cidade;
	  private String estado;
	  private String endereco;
	  private Boolean cliente;
	  private Boolean psicologo;
	  private Boolean confirmado;
	  private Boolean pessoaImportada;
	  private String nomeResponsavelImportacao;
	  private String empresaImportacao;
	  private String relacaoContato;
	  private String nomeContato;
	  private String celularContato;
	  private String cpfTitular;
	  private Boolean clienteAtivo;
	  @Transient
	  private String tipoConta;
	  
	  @Transient
	  private String nomeTitular;
	  
	  @ManyToOne
	  @JoinColumn(nullable = true)
	  private Arquivo foto;
	  
	  private String dataNascimento;
	  private Date dataCadastro;
	  
	  @Transient
	  private String operacaoImportacao;

	  private String classificacaoImportacao;
	  
	  @Transient
	  private Boolean alteracaoIlionnet;
	
	public Long getId() {
		if (id == null) {
			id = 0l;
		}
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		if (nome == null) {
			nome = "";
		}
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		if (email == null) {
			email = "";
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		if (telefone == null) {
			telefone = "";
		}
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		if (celular == null) {
			celular = "";
		}
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCpf() {
		if (cpf == null) {
			cpf = "";
		}
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		if (senha == null) {
			senha = "";
		}
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCep() {
		if (cep == null) {
			cep = "";
		}
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getSetor() {
		if (setor == null) {
			setor = "";
		}
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCidade() {
		if (cidade == null) {
			cidade = "";
		}
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEndereco() {
		if (endereco == null) {
			endereco = "";
		}
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Boolean getCliente() {
		if (cliente == null) {
			cliente = Boolean.FALSE;
		}
		return cliente;
	}
	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}
	public Boolean getPsicologo() {
		if (psicologo == null) {
			psicologo = Boolean.FALSE;
		}
		return psicologo;
	}
	public void setPsicologo(Boolean psicologo) {
		this.psicologo = psicologo;
	}
	public Arquivo getFoto() {
		return foto;
	}
	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataCadastro() {
		if (dataCadastro == null) {
			dataCadastro = new Date();
		}
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getEstado() {
		if (estado == null) {
			estado = "";
		}
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getGenero() {
		if(genero == null) {
			genero = "";
		}
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public Boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
	  
	  
	public String getToken() {
		return StringUtil.encodePassword(getEmail());
	}
	public Boolean getPessoaImportada() {
		if(pessoaImportada == null) {
			pessoaImportada = Boolean.FALSE;
		}
		return pessoaImportada;
	}
	public void setPessoaImportada(Boolean pessoaImportada) {
		this.pessoaImportada = pessoaImportada;
	}
	public String getNomeResponsavelImportacao() {
		if(nomeResponsavelImportacao == null) {
			nomeResponsavelImportacao = "";
		}
		return nomeResponsavelImportacao;
	}
	public void setNomeResponsavelImportacao(String nomeResponsavelImportacao) {
		this.nomeResponsavelImportacao = nomeResponsavelImportacao;
	}
	public String getEmpresaImportacao() {
		if(empresaImportacao == null) {
			empresaImportacao = "";
		}
		return empresaImportacao;
	}
	public void setEmpresaImportacao(String empresaImportacao) {
		this.empresaImportacao = empresaImportacao;
	}
	public String getRelacaoContato() {
		if (relacaoContato == null) {
			relacaoContato = "";
		}
		return relacaoContato;
	}
	public void setRelacaoContato(String relacaoContato) {
		this.relacaoContato = relacaoContato;
	}
	public String getNomeContato() {
		if (nomeContato == null) {
			nomeContato = "";
		}
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	public String getCelularContato() {
		if (celularContato == null) {
			celularContato = "";
		}
		return celularContato;
	}
	
	public String getCpfTitular() {
		if (cpfTitular == null) {
			cpfTitular = "";
		}
		return cpfTitular;
	}
	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}
	public void setCelularContato(String celularContato) {
		this.celularContato = celularContato;
	}
	public Boolean getClienteAtivo() {
		if(clienteAtivo == null) {
			clienteAtivo = Boolean.FALSE;
		}
		return clienteAtivo;
	}
	public void setClienteAtivo(Boolean clienteAtivo) {
		this.clienteAtivo = clienteAtivo;
	}
	
	public String getOperacaoImportacao() {
		return operacaoImportacao;
	}
	public void setOperacaoImportacao(String operacaoImportacao) {
		this.operacaoImportacao = operacaoImportacao;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getClassificacaoImportacao() {
		return classificacaoImportacao;
	}
	public void setClassificacaoImportacao(String classificacaoImportacao) {
		this.classificacaoImportacao = classificacaoImportacao;
	}
	public Boolean getAlteracaoIlionnet() {
		if(alteracaoIlionnet == null) {
			alteracaoIlionnet = Boolean.FALSE;
		}
		return alteracaoIlionnet;
	}
	public void setAlteracaoIlionnet(Boolean alteracaoIlionnet) {
		this.alteracaoIlionnet = alteracaoIlionnet;
	}
	public String getNomeTitular() {
		if (nomeTitular == null) {
			nomeTitular = "";
		}
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

}
