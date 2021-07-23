package ilion.vitazure.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import ilion.me.pagar.model.BankAccount;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.PagarMeException;
import ilion.me.pagar.model.Recipient;

import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.PropEnum;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AcessoLivre
public class PagarMeController {

  @Autowired
  private PropNegocio propNegocio;

  @GetMapping(value = "/v1/getAPI", produces = "text/plain")
  @ResponseBody
  public ResponseEntity<String> getAPI(HttpServletRequest request) {

    String API = propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY);

    try {
      return new ResponseEntity<>(API, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/v1/registrar-cartao")
  public ResponseEntity<Boolean>  salvarCartao(HttpServletRequest request) {

    PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
    BankAccount bankAccount = new BankAccount();


    try {
      return new ResponseEntity<>(true, HttpStatus.OK);
    }catch (Exception e) {
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

  }

}
