package ilion.util.contexto.autorizacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ilion.util.exceptions.NaoAutenticadoException;
import ilion.util.exceptions.NaoAutorizadoException;

public class AuthInterceptor implements HandlerInterceptor {
	
	static Logger logger = Logger.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if( request.getRequestURI().endsWith("/error") ) {
			return true;
		}
		
		if( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			try {
				
				AutorizacaoTipoEnum autorizacaoTipoEnum = obterAnotacaoExistente(response, handlerMethod);
				
				IAutorizacaoVerificacao autorizacaoVerificacao = autorizacaoTipoEnum.getAutorizacaoVerificacao();
				
				autorizacaoVerificacao.executar(request, response, handlerMethod);
				
			} catch (AcessoLiberadoException e) {
				
				return true;
			
			} catch (NaoAutorizadoException e) {
				
				throw e;
				
			} catch (Exception e) {
				
				throw e;
				
			}
			
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	}
	
	private AutorizacaoTipoEnum obterAnotacaoExistente(HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		AutorizacaoTipoEnum retorno = null;
		
		for (AutorizacaoTipoEnum autorizacaoTipoEnum : AutorizacaoTipoEnum.values()) {
			
			Object annotation = obterAnotacaoAutorizacao(handlerMethod, autorizacaoTipoEnum.getClazzAnnotation());
			if( annotation != null ) {
				retorno = autorizacaoTipoEnum;
				break;
			}
			
		}
		
		if( retorno == null ) {
//			Uteis.escreverResposta("permissao-nao-definida", false, response);
			throw new NaoAutenticadoException();
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public static Object obterAnotacaoAutorizacao(HandlerMethod handlerMethod, Class clazzAnotation) {
		
		//inicialmente é buscado uma anotação específica para o método
		Object obj = handlerMethod.getMethod().getAnnotation(clazzAnotation);
		
		if( obj != null ) {
			return obj;
		}
		
		//caso não encontre anotação no método é buscado uma anotação na classe 
		return handlerMethod.getMethod().getDeclaringClass().getAnnotation(clazzAnotation);
	}
}
