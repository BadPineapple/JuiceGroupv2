package ilion.util.contexto.autorizacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.view.RedirectView;

import ilion.vitazure.negocio.PessoaNegocio;


public class PessoaLogadaAutorizacaoVerificacao implements IAutorizacaoVerificacao {

	static Logger logger = Logger.getLogger(PessoaLogadaAutorizacaoVerificacao.class);
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		Object pessoaSessao = request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		
		if( pessoaSessao == null ) {
			
			PessoaLogada pessoaLogada = (PessoaLogada) AuthInterceptor.obterAnotacaoAutorizacao(handlerMethod, PessoaLogada.class);
			
			ModelAndView mav = new ModelAndView(new RedirectView(pessoaLogada.redirectTo()));
            
            throw new ModelAndViewDefiningException(mav);
		}
		
	}
}
