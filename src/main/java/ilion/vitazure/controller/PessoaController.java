package ilion.vitazure.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.json.JsonString;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.negocio.PessoaNegocio;

@Controller
@SessionAttributes("pessoa")
@AcessoLivre
public class PessoaController {

	static Logger logger = Logger.getLogger(PessoaController.class);
	
	@Autowired
	private PessoaNegocio  pessoaNegocio;
	
	  @PostMapping(value = "/vitazure/pessoa", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> salvar(HttpServletRequest request,@RequestBody Pessoa pessoa) {
	      try {
	    	  pessoa = pessoaNegocio.incluirAtualizar(pessoa);
	    	  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			return new ResponseEntity<>(new JsonString("Categoria salva com sucesso!"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
	    } 
	  }
	  @PostMapping(value = "/vitazure/login", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> login(HttpServletRequest request,@RequestBody Pessoa pessoa) {
		  try {
			  pessoa = pessoaNegocio.login(pessoa);
			  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			  return new ResponseEntity<>(new JsonString("ok"), HttpStatus.OK);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
		  } 
	  }
	
	  
	  
	
}
