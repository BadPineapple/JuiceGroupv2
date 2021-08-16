package ilion.vitazure.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.json.JsonString;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;

@Controller
@SessionAttributes("pessoa")
@AcessoLivre
public class PessoaController {

	static Logger logger = Logger.getLogger(PessoaController.class);
	
	@Autowired
	private PessoaNegocio  pessoaNegocio;
	
	@Autowired
	private ProfissionalNegocio profissionalNegocio;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	@Autowired
	private AgendaNegocio agendaNegocio; 
	
	  @PostMapping(value = "/vitazure/pessoa", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> salvar(HttpServletRequest request,@RequestBody Pessoa pessoa) {
	      try {
	    	  if (pessoa.getFoto() != null && !pessoa.getFoto().getArquivo1().equals("")) {
	    		  pessoa.setFoto(arquivoNegocio.inserir(pessoa.getFoto()));
	    	  }else if(pessoa.getFoto() == null || pessoa.getFoto().getId().equals("")) {
	    		  pessoa.setFoto(null);
	    	  }
	    	  pessoa = pessoaNegocio.incluirAtualizar(pessoa);
	    	  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			return new ResponseEntity<>(new JsonString("Cadastro salvo com sucesso!"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
	    } 
	  }
	  @PostMapping(value = "/vitazure/login", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> login(ModelMap modelMap,HttpServletRequest request,@RequestBody Pessoa pessoa) {
		  try {
			  pessoa = pessoaNegocio.login(pessoa);
			  List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
			  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			  request.getSession().setAttribute(PessoaNegocio.AGENDA_SESSAO, listAgendaDia);
			  return new ResponseEntity<>(new JsonString("Logadando"), HttpStatus.OK);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
		  } 
	  }
	
	  @PostMapping(value = "/vitazure/ilionnet/perfilProfissional", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> atualizarPerfilProfissional(HttpServletRequest request,@RequestBody Profissional profissional) {
	      try {
	    	  profissional.setPessoa(pessoaNegocio.incluirAtualizar(profissional.getPessoa()));
	    	  profissional = profissionalNegocio.incluirAtualizar(profissional);
	    	  
	    	  return new ResponseEntity<>(new JsonString("Perfil Profissional Atualizado!"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
	    } 
	  }
	  
	  @PostMapping(value = "/vitazure/esqueciMinhaSenha", produces = "application/json")
	    public ResponseEntity<JsonString>  esqueciMinhaSenha(
	    		@RequestBody String email, 
	    		HttpServletRequest request, 
	    		RedirectAttributes redirectAttributes
	    		) throws Exception {
	    	
	    	try {
	    		pessoaNegocio.enviarSenhaEmail(email);
	    		redirectAttributes.addFlashAttribute("msg", "Senha enviada ao email: "+email);
	    		 return new ResponseEntity<>(new JsonString("Senha enviada ao email!"), HttpStatus.OK);
	    		
	    	} catch (ValidacaoException e) {
	    		redirectAttributes.addFlashAttribute("msg", e.getMessage());
	    		 return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
	    		
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("msg", "Erro ao tentar enviar a senha, por favor tente novamente em instantes.");
				return new ResponseEntity<>(new JsonString("Erro ao tentar enviar a senha, por favor tente novamente em instantes."), HttpStatus.BAD_REQUEST);
			}
	    	
	    }
	
}
