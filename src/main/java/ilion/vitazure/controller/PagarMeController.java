package ilion.vitazure.controller;


import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import ilion.me.pagar.model.*;
import ilion.util.json.JsonString;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.negocio.PagarMeNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.PropEnum;

@RestController
@AcessoLivre
public class PagarMeController {

  @Autowired
  private PropNegocio propNegocio;

  @Autowired
  private PagarMeNegocio pagarMeNegocio;
  
  @Autowired
  private ProfissionalNegocio profissionalNegocio;

  private String jsonString;

  @GetMapping(value = "/vitazure/api/v1/getencryption", produces = "application/x-www-form-urlencoded")
  @ResponseBody
  public ResponseEntity<String> getAPI(HttpServletRequest request) {

    String API = propNegocio.findValueById(PropEnum.PAGAR_ME_ENCRYPTION_KEY);

    try {
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
      return new ResponseEntity<>(new JsonString("Erro ao cadastrar a conta, verifique as informações"), HttpStatus.BAD_REQUEST);
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
			
			profissionalNegocio.incluirAtualizar(profissional);
			
			Transaction capturarTransacao = new Transaction().find(tx.getId());

			capturarTransacao.capture(tx.getAmount());
			return new ResponseEntity<>("Incluido Plano com Sucesso", HttpStatus.OK);
	  }
	  catch (Exception e) {
		  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
  }

}
