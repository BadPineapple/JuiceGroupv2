package ilion;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ilion.util.Uteis;
import ilion.util.exceptions.NaoAutorizadoException;

public class CustomErrorController {
	
	static Logger logger = Logger.getLogger(CustomErrorController.class);
	
	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, HttpServletRequest request) {
		
		logger.error("", ex);
		
		String stacktrace = Uteis.throwableInformation(ex);
		
		request.setAttribute("exception", stacktrace);
		
		return "/error";
	}
	
	@ExceptionHandler(NaoAutorizadoException.class)
	public String naoAutorizado(NaoAutorizadoException ex, HttpServletRequest request) {
		
		logger.error("", ex);
		
		String stacktrace = Uteis.throwableInformation(ex);
		
		request.setAttribute("exception", stacktrace);
		
		return "/acesso-nao-autorizado";
	}
	
//	@ExceptionHandler(NotFoundException.class)
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	public String notFound(Exception ex, HttpServletResponse response) {
//		
//		logger.error(ex.getMessage());
//		
//		return "404";
//	}
	
}