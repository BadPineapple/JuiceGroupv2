package ilion.util.contexto.autorizacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;

import ilion.SpringApplicationContext;
import ilion.util.JWTUtil;
import ilion.util.Uteis;
import ilion.util.exceptions.NaoAutorizadoException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class UsuarioAppTokenAutorizacaoVerificacao implements IAutorizacaoVerificacao {
	
	static Logger logger = Logger.getLogger(UsuarioAppTokenAutorizacaoVerificacao.class);
	
	public final static String HEADER_AUTORIZACAO = "Authorization";
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
		
		String headerAutorization = request.getHeader(HEADER_AUTORIZACAO);
		
		Long idUsuario = conferirAutorizacao(headerAutorization);
		
		request.setAttribute("idUsuario", idUsuario);
		
	}
	
	public static Long conferirAutorizacao(String authorization) {
		
		if( Uteis.ehNuloOuVazio(authorization) ) {
			throw new NaoAutorizadoException("Token inválido!");
		}
		
		Long idUsuario = null;
		
		try {
			
			JWTUtil jwtUtil = SpringApplicationContext.getBean(JWTUtil.class);
			Jws<Claims> jwt = jwtUtil.decode(authorization);
			
			idUsuario = Uteis.converterLong( jwt.getBody().getSubject() );
			
		} catch (Exception e) {
			logger.error("Token inválido: "+authorization, e);
			throw new NaoAutorizadoException("Token inválido!");
		}
		
		if( idUsuario == null ) {
			throw new NaoAutorizadoException("Token inválido!");
		}
		
		return idUsuario;
	}
	
}
