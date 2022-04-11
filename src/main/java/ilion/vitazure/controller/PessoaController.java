package ilion.vitazure.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import ilion.email.negocio.EmailNegocio;
import ilion.util.Uteis;
import org.apache.log4j.Logger;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
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

import ilion.admin.negocio.Usuario;
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

			  pessoa.setEmail(pessoa.getEmail().toLowerCase(Locale.ROOT));

	    	  if (pessoa.getEmail() != null && !pessoa.getEmail().equals("") && !Uteis.ehEmailValido(pessoa.getEmail())) {
	    	  	return new ResponseEntity<>(new JsonString("E-mail informado inválido."), HttpStatus.BAD_REQUEST);
			  }

	    	  if (pessoa.getCelular() != null && !pessoa.getCelular().equals("") && !(pessoa.getCelular().matches("^\\((?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\) (?:[2-8]|9[1-9])[0-9]{3}-[0-9]{4}$"))) {
	    	  	return new ResponseEntity<>(new JsonString("Número de telefone inválido. Verifique se o DDD está correto"), HttpStatus.BAD_REQUEST);
					}

	    	  Boolean containNumber = false;
					Boolean containUpperCase = false;

					char[] chars = pessoa.getSenha().toCharArray();
					StringBuilder sb = new StringBuilder();
					for(char c : chars){
						if(Character.isDigit(c)){
							containNumber = true;
						}
						if(Character.isUpperCase(c)) {
							containUpperCase = true;
						}
					}

	    	  if (pessoa.getSenha().length() < 8 || !containNumber || !containUpperCase) {
	    	  	return new ResponseEntity<>(new JsonString("A senha deve conter no mínimo 8 caracteres, uma letra maiúscula e um número."), HttpStatus.BAD_REQUEST);
					}
	  		  Pessoa pessoaImportada =  pessoaNegocio.consultarPorCpf(pessoa.getCpf());
	  		  if(pessoaImportada != null && pessoa.getId() == 0 && pessoaImportada.getPessoaImportada()) {
	  			pessoa.setId(pessoaImportada.getId());
	  			pessoa.setEmpresaImportacao(pessoaImportada.getEmpresaImportacao());
	  			pessoa.setConfirmado(Boolean.FALSE);
	  			pessoa.setNomeResponsavelImportacao(pessoaImportada.getNomeResponsavelImportacao());
	  			pessoa.setPessoaImportada(pessoaImportada.getPessoaImportada());
	  		  }
	  		  if(pessoaImportada == null && pessoa.getTipoConta().equals("CO")) {
	  			return new ResponseEntity<>(new JsonString("CPF não existe na base de dados, entre em contato com sua empresa."), HttpStatus.BAD_REQUEST);
	  		  }
	    	  pessoa = pessoaNegocio.incluirAtualizar(pessoa);
	    	  pessoaNegocio.enviarEmailAtivacao(pessoa);
	    	  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			return new ResponseEntity<>(new JsonString(pessoa.getAlteracaoIlionnet() ? "Dados alterado com sucesso.": "Para continuar o cadastro confirme o link enviado por e-mail."), HttpStatus.OK);
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
			  String idProfissional = (String) request.getSession().getAttribute("idProfissional");
			  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			  request.getSession().setAttribute("idProfissional", idProfissional);
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
	    	  if(profissional.getPessoa().getFoto() == null || profissional.getPessoa().getFoto().getId().equals("")) {
	    		  profissional.getPessoa().setFoto(null);
	    	  }
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
	  
	  @PostMapping(value = "/vitazure/completarCadastro", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> completarCadastro(HttpServletRequest request,@RequestBody Pessoa pessoa) {
	      try {
	    	  if (pessoa.getFoto() != null && !pessoa.getFoto().getArquivo1().equals("")) {
	    		  pessoa.setFoto(arquivoNegocio.inserir(pessoa.getFoto()));
	    	  }else if(pessoa.getFoto() == null || pessoa.getFoto().getId().equals("")) {
						pessoa.setFoto(null);
	    	  }	 
	    	  pessoa = pessoaNegocio.incluirAtualizar(pessoa);
	    	  request.getSession().setAttribute(PessoaNegocio.ATRIBUTO_SESSAO, pessoa);
			return new ResponseEntity<>(new JsonString("Dados Salvo com Sucesso."), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
	    } 
	  }
	
}
