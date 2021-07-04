package ilion.util.contexto.autorizacao;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.view.RedirectView;

import ilion.admin.negocio.Usuario;
import ilion.util.exceptions.NaoAutorizadoException;

public class UsuarioLogadoAutorizacaoVerificacao implements IAutorizacaoVerificacao {

	static Logger logger = Logger.getLogger(UsuarioLogadoAutorizacaoVerificacao.class);
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		UsuarioLogado usuarioLogado = (UsuarioLogado) AuthInterceptor.obterAnotacaoAutorizacao(handlerMethod, UsuarioLogado.class);
		
		Object usuarioSessao = request.getSession().getAttribute("usuarioSessao");
		
		if( usuarioSessao == null ) {
			//throw new NaoAutenticadoException();
			ModelAndView mav = new ModelAndView(new RedirectView("/ilionnet/login"));
            
            throw new ModelAndViewDefiningException(mav);
		}
		
		String[] permissoes = usuarioLogado.value();
		
		if( permissoes == null || permissoes.length == 0 ) {
			throw AcessoLiberadoException.getInstance();
		}
		
		Usuario usuario = (Usuario) usuarioSessao;
		
		Boolean possuiPermissao = verificarPossuiPermissao(usuario, permissoes);
		
		if( ! possuiPermissao ) {
			logger.info("usuario-nao-permitido: usuario.email: "+usuario.getEmail()+", "+Arrays.asList(permissoes)+", "+handlerMethod.toString());
			//acaoParaPermissaoNegada(response, usuarioLogado);
			throw new NaoAutorizadoException("");
		}
		
	}
	
	private Boolean verificarPossuiPermissao(Usuario usuario, String[] permissoes) {

		Boolean possuiPermissao = false;

		for (String permissao : permissoes) {

			if( usuario.getPermissoes().contains( permissao ) ) {
				possuiPermissao = true;
			}

		}

		return possuiPermissao;
	}
}
