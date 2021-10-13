package ilion.vitazure.controller;


import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.Postback;
import ilion.me.pagar.model.Transaction;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.util.json.JsonString;
import ilion.vitazure.enumeradores.SituacaoAprovacaoProfissionalEnum;
import ilion.vitazure.model.PagamentoPagarMe;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.EnvioEmailConsulta;
import ilion.vitazure.negocio.PagarMeNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;

@RestController
@AcessoLivre
public class PagarMeController {

  @Autowired
  private PropNegocio propNegocio;

  @Autowired
  private PagarMeNegocio pagarMeNegocio;
  
  @Autowired
  private ProfissionalNegocio profissionalNegocio;
  
  @Autowired
  private AgendaNegocio agendaNegocio;
  
  @Autowired
  private EnvioEmailConsulta envioEmailConsulta;
  

  private String jsonString;

  @GetMapping(value = "/api/v1/getencryption", produces = "application/x-www-form-urlencoded")
  @ResponseBody
  public ResponseEntity<String> getAPI(HttpServletRequest request) {

    String API = propNegocio.findValueById(PropEnum.PAGAR_ME_ENCRYPTION_KEY);
    try {
      Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
      if(pessoa == null) {
    	  String idProfissional = request.getParameter("idProfissional");
    	  request.getSession().setAttribute("idProfissional", idProfissional);
    	  return new ResponseEntity<>("true", HttpStatus.BAD_REQUEST); 
      }else if(pessoa.getPsicologo()) {
    	  return new ResponseEntity<>("Não é possível fazer agendamento com uma conta do Tipo Profissional.", HttpStatus.BAD_REQUEST);  
      }
      return new ResponseEntity<>(API, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/api/v1/registrar-cartao", produces = "application/json")
  @ResponseBody
  public ResponseEntity<JsonString> salvarCartao(HttpServletRequest request, @RequestBody Profissional profissional) {
    try {
    	
    String resposta = pagarMeNegocio.cadastraRecebedor(profissional);

    if(resposta.contains("Erro")) {
      return new ResponseEntity<>(new JsonString(resposta), HttpStatus.BAD_REQUEST);
    }
      return new ResponseEntity<>(new JsonString("Conta cadastrada com sucesso"), HttpStatus.OK);
    }catch (Exception e) {
      return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

  }

  @PostMapping(value = "/api/v1/retornoPagarMe", produces = "application/json")
  @ResponseBody
  public ResponseEntity<String> receberPostback(HttpServletRequest request, @RequestBody String postback) {
    try {

      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
      Postback teste = new Postback();
      teste.setPayload(postback);
      pagarMeNegocio.salvarRetorno(teste);
      String transactionId = teste.getPayload().substring(3, teste.getPayload().indexOf("&"));

      return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @PostMapping(value = "/api/v1/salvarContratacaoPlano", produces = "application/json")
  @ResponseBody
  public ResponseEntity<String> salvarContratacaoPlano(HttpServletRequest request, @RequestBody String  retornoToken) {
	  try {
		  
		  JSONObject teste = new JSONObject(retornoToken);
		  
		  String payment_method = teste.get("payment_method").toString();
		  String token = teste.get("token").toString();
		  
		  PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
		  Transaction tx = new Transaction().find(token);
		  
		    Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
			Profissional profissional = new Profissional();
			
			profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
			if (profissional == null || profissional.getId() == 0) {
				profissional.setPessoa(pessoa);
			}
			
			profissional.setAtivo(Boolean.TRUE);
			profissional.setDataInicioPlano(Uteis.formatarDataHora(new Date(), "dd-MM-YYYY"));
			profissional.setPlano(teste.get("plano").toString());
			profissional.setDataFimPlano(Uteis.formatarDataHora(Uteis.acrescentar(new Date(), Calendar.DATE, profissional.getQuantidadesDiasVencimentoPlano()) , "dd-MM-YYYY"));
			profissional.setTokenTransacaoPlano(token);
			profissional.setIdTransacao(tx.getId());
			if(!profissional.getSituacaoAprovacaoProfissional().equals(SituacaoAprovacaoProfissionalEnum.PENDENTE)) {
				profissional.setSituacaoAprovacaoProfissional(SituacaoAprovacaoProfissionalEnum.PENDENTE);
				envioEmailConsulta.enviarMensagemAutorizarProfissional(profissional);
			}
			profissionalNegocio.incluirAtualizar(profissional);
			Transaction capturarTransacao = new Transaction().find(tx.getId());
			capturarTransacao.capture(tx.getAmount());
			PagamentoPagarMe pagamentoPagarMe = new PagamentoPagarMe();
			pagamentoPagarMe = pagamentoPagarMe.pagamento(capturarTransacao, null, profissional);
			pagarMeNegocio.salvarPagamentoPagarMe(pagamentoPagarMe);
			return new ResponseEntity<>("Incluido Plano com Sucesso", HttpStatus.OK);
	  }
	  catch (Exception e) {
		  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
  }
  
}
