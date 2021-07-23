package ilion.vitazure.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.util.json.JsonString;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;
import ilion.vitazure.negocio.ProfissionalVH;

@Controller
@SessionAttributes("pessoaSessao")
@PessoaLogada
public class ProfissionalControlle {

	static Logger logger = Logger.getLogger(PessoaController.class);
	
	@Autowired
	private ProfissionalNegocio profissionalNegocio;
	
	@Autowired
	private PessoaNegocio pessoaNegocio;

	@Autowired
	private EnderecoNegocio enderecoNegocio;

	@Autowired
	private FormacaoAcademicaNegocio formacaoAcademicaNegocio;
	
	@Autowired
	private ArquivoUteis arquivosUteis;
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	private Gson gson = new Gson();
	
      @PostMapping(value = "/vitazure/perfilProfissional", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<JsonString> atualizarPerfilProfissional(HttpServletRequest request,@RequestBody ProfissionalVH profissionalVH) {
	      try {
	    	  if (profissionalVH.getProfissional().getPessoa().getFoto() != null && !profissionalVH.getProfissional().getPessoa().getFoto().getArquivo1().equals("")) {
	    		  profissionalVH.getProfissional().getPessoa().setFoto(arquivoNegocio.inserir(profissionalVH.getProfissional().getPessoa().getFoto()));
	    	  }	
	    	  profissionalVH.getProfissional().setPessoa(pessoaNegocio.incluirAtualizar(profissionalVH.getProfissional().getPessoa()));
	    	  profissionalVH.setProfissional(profissionalNegocio.incluirAtualizar(profissionalVH.getProfissional()));
	    	  formacaoAcademicaNegocio.validarItens(profissionalVH.getFormacaoAcademica(), profissionalVH.getProfissional());
	    	  enderecoNegocio.validarItens(profissionalVH.getEnderecoAtendimento(), profissionalVH.getProfissional());
	    	  
	    	  return new ResponseEntity<>(new JsonString("Perfil Profissional Atualizado!"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
	    } 
	  }
      
      
      @PostMapping(value = "/vitazure/imagem", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<String>  UploadImagemCapaCategoria(MultipartHttpServletRequest request) {

	    List<MultipartFile> files = request.getFiles("files");
	    String path =  propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
	    try {
	      List<Arquivo> arquivos = arquivosUteis.uploadArquivos(files, path, "fotos");
	      Arquivo arquivo = new Arquivo();
	      if (arquivos != null && arquivos.size() > 0) {
	    	  arquivo = arquivos.get(0);
	      }
	      return new ResponseEntity<>(gson.toJson(arquivo), HttpStatus.OK);
	    } catch (ValidationException e) {
	      e.printStackTrace();
	      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	  }
      
      @GetMapping("/vitazure/listaProfissionais")
	  	public String souProfissional(HttpServletRequest request) {
	  		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
	  		List<Profissional> lisProfissional = profissionalNegocio.consultarProfissionais();
	  		request.setAttribute("pessoa", PessoaSessao);
	  		request.setAttribute("areaRestrita", true);
	  		request.getSession().setAttribute("listProfissionais", lisProfissional);
			return "/ilionnet2/vitazure/resultado-de-busca";
	  	}
}
