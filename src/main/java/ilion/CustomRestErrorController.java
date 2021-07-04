package ilion;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ilion.util.exceptions.NaoAutenticadoException;
import ilion.util.exceptions.NaoAutorizadoException;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.json.APIResponse;

public class CustomRestErrorController {
	
	static Logger logger = Logger.getLogger(CustomRestErrorController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<APIResponse> errorResponse(Exception ex, HttpServletResponse response) {
		
		APIResponse apiResponse = APIResponse.errorWithMessage(ex.getMessage());
		
		logger.error("", ex);
		
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidacaoException.class)
	@ResponseBody
	public ResponseEntity<APIResponse> errorValidacaoResponse(Exception ex, HttpServletResponse response) {
		
		APIResponse apiResponse = APIResponse.errorWithMessage(ex.getMessage());
		
		logger.error("ValidacaoException: "+ex.getMessage());
		
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value= {
		NaoAutorizadoException.class,
		NaoAutenticadoException.class
	})
	@ResponseBody
	public ResponseEntity<APIResponse> errorNaoAutorizado(Exception ex, HttpServletResponse response) {
		
		APIResponse apiResponse = APIResponse.errorWithMessage(ex.getMessage());
		
		logger.error("usuario-nao-autorizado");
		
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
	}
	
}