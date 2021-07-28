package ilion.vitazure.controller;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.xml.ws.Response;

import ilion.me.pagar.model.*;
import ilion.util.StringUtil;
import ilion.util.json.JsonString;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.negocio.PagarMeNegocio;
import org.apache.http.protocol.HTTP;
import org.hibernate.jdbc.Expectation;
import org.springframework.http.HttpStatus;

import ilion.vitazure.negocio.ProfissionalVH;

import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.PropEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RestController
@AcessoLivre
public class PagarMeController {

  @Autowired
  private PropNegocio propNegocio;

  @Autowired
  private PagarMeNegocio pagarMeNegocio;

  @GetMapping(value = "/api/v1/getAPI", produces = "text/plain")
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

  @PostMapping(value = "/api/v1/postback", produces = "application/json")
  @ResponseBody
  public ResponseEntity<JsonString> receberPostback(HttpServletRequest request, @RequestBody Postback postback) {
    try {
      String response = postback.getPayload();

      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

      String transactionId = response.substring(3, response.indexOf("&"));

      return new ResponseEntity<>(new JsonString("Sucesso"), HttpStatus.OK);
    }
    catch (Exception e) {
      return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

}
