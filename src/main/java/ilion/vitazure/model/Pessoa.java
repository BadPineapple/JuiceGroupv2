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

import ilion.arquivo.negocio.Arquivo;

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
	  
	  @ManyToOne
	  @JoinColumn(nullable = true)
	  private Arquivo foto;
	  
	  private String dataNascimento;
	  private Date dataCadastro;
	
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
	  
	  
	  
	  
	  
	

}
