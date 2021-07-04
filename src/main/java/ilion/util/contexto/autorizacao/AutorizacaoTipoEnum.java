package ilion.util.contexto.autorizacao;

public enum AutorizacaoTipoEnum {
	
	ACESSO_LIVRE(AcessoLivre.class, new AcessoLivreAutorizacaoVerificacao()),
	USUARIO_LOGADO(UsuarioLogado.class, new UsuarioLogadoAutorizacaoVerificacao()),
	USUARIO_APP_TOKEN(UsuarioAppToken.class, new UsuarioAppTokenAutorizacaoVerificacao()),
	;
	
	private Class clazzAnnotation;
	private IAutorizacaoVerificacao autorizacaoVerificacao;
	
	private AutorizacaoTipoEnum(Class clazzAnnotation, IAutorizacaoVerificacao autorizacaoVerificacao) {
		this.clazzAnnotation = clazzAnnotation;
		this.autorizacaoVerificacao = autorizacaoVerificacao;
	}

	public Class getClazzAnnotation() {
		return clazzAnnotation;
	}

	public IAutorizacaoVerificacao getAutorizacaoVerificacao() {
		return autorizacaoVerificacao;
	}
	
}