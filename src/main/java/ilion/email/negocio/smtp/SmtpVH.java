package ilion.email.negocio.smtp;

public class SmtpVH {
	
	private String server;
	
	private Integer porta;
	
	private String email;
	
	private String usuario;
	
	private String senha;
	
	private Boolean usarSSL;
	
	private Integer sslSmtpPort;
	
	private Boolean usarTLS;
	
	private String charset;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Integer getPorta() {
		return porta;
	}

	public void setPorta(Integer porta) {
		this.porta = porta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getUsarSSL() {
		return usarSSL;
	}

	public void setUsarSSL(Boolean usarSSL) {
		this.usarSSL = usarSSL;
	}

	public Integer getSslSmtpPort() {
		return sslSmtpPort;
	}

	public void setSslSmtpPort(Integer sslSmtpPort) {
		this.sslSmtpPort = sslSmtpPort;
	}

	public Boolean getUsarTLS() {
		return usarTLS;
	}

	public void setUsarTLS(Boolean usarTLS) {
		this.usarTLS = usarTLS;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	
}