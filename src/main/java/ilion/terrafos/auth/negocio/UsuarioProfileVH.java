package ilion.terrafos.auth.negocio;

import ilion.admin.negocio.Usuario;

public class UsuarioProfileVH {
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String cpf;
	
	private String ddd;
	
	private String telefone;
	
	private String dtNascimento;
	
	public UsuarioProfileVH() {
		super();
	}

	public UsuarioProfileVH(Usuario u) {
		super();
		this.id = u.getId();
		this.nome = u.getNome();
		this.telefone = u.getTelefone();
		this.email = u.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	@Override
	public String toString() {
		return "UsuarioProfileVH [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}

}