package ilion.util.contexto.autorizacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

public interface IAutorizacaoVerificacao {

	public void executar(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception;
	
}