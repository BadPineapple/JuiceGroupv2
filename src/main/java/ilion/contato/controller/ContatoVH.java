package ilion.contato.controller;

import java.util.List;

import ilion.contato.negocio.ArquivoBase64VH;

public class ContatoVH {
	
	private String nome;
	private String empresa;
	private String email;
	private String telefone;
	private String celular;
	private String dataNascimento;
	private String cidade;
	private String estado;
	private String endereco;
	private String assunto;
	private String mensagem;
	
	private String origem;
	private String destino;
	
	private String dataIda;
	private String dataRetorno;
	
	private String grupo;
	
	private Boolean permissaoEmail;
	
	private String codigoValidador;
	
	private List<ArquivoBase64VH> arquivos;
	
	//uso interno no Ilionnet
	private Boolean cadastrarComentario;
	
	//uso interno no Ilionnet
	private Boolean enviarEmail;
	
	private Boolean ouvidoria;
	
	public ContatoVH() {
		super();
		grupo = "Visitante do site";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Boolean getPermissaoEmail() {
		return permissaoEmail;
	}
	public void setPermissaoEmail(Boolean permissaoEmail) {
		this.permissaoEmail = permissaoEmail;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getCodigoValidador() {
		return codigoValidador;
	}

	public void setCodigoValidador(String codigoValidador) {
		this.codigoValidador = codigoValidador;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getCadastrarComentario() {
		return cadastrarComentario;
	}

	public void setCadastrarComentario(Boolean cadastrarComentario) {
		this.cadastrarComentario = cadastrarComentario;
	}

	public Boolean getEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(Boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public List<ArquivoBase64VH> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoBase64VH> arquivos) {
		this.arquivos = arquivos;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDataIda() {
		return dataIda;
	}

	public void setDataIda(String dataIda) {
		this.dataIda = dataIda;
	}

	public String getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Boolean getOuvidoria() {
		if (ouvidoria == null) {
			ouvidoria = false;
		}
		return ouvidoria;
	}

	public void setOuvidoria(Boolean ouvidoria) {
		this.ouvidoria = ouvidoria;
	}

	
}