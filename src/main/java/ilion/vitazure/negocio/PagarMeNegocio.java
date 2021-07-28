package ilion.vitazure.negocio;


import javax.servlet.http.HttpServletRequest;

import ilion.me.pagar.BankAccountType;
import ilion.me.pagar.RecipientStatus;
import ilion.me.pagar.model.BankAccount;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.PagarMeException;
import ilion.me.pagar.model.Recipient;
import ilion.util.Uteis;
import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.model.Profissional;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.PropEnum;

@Service
@SuppressWarnings("unchecked")
public class PagarMeNegocio {

  @Autowired
  private PropNegocio propNegocio;

  @Autowired
  private ProfissionalNegocio profissionalNegocio;


  public String cadastraRecebedor (Profissional profissional) {

    BankAccount account = cadastraConta(profissional);

    if(account == null) {
      return "Erro ao cadastrar conta bancária";
    }

    try {

      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

      Recipient recipient = consultaRecebedor(profissional.getPessoa().getId());

      if (recipient.getStatus() != RecipientStatus.ACTIVE) {
        recipient.setBankAccount(account);
        recipient.setTransferInterval(Recipient.TransferInterval.MONTHLY);
        recipient.setTransferDay(5);
        recipient.setTransferEnabled(true);
      }else {
        recipient.setBankAccountId(account.getId());
      }
      recipient.save();

      profissional.setIdRecebedor(recipient.getId());
      try {
        profissionalNegocio.incluirAtualizar(profissional);
      }
      catch (Exception e) {
        return "Erro ao salvar o cadastro";
      }
      return "Cadastrado com sucesso";
    }
    catch(PagarMeException e) {
      return "Erro ao cadastrar recebedor";
    }

  }

  private BankAccount cadastraConta(Profissional profissional) {

    PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

    try {
      BankAccount account = consultaConta(profissional.getPessoa().getId());

      if(account != null) {
        account = new BankAccount();
      }


      account.setAgencia(profissional.getAgencia());

      String banco = BancoEnum.getCodigoFromType(profissional.getBanco().toString());

      account.setBankCode(banco);
      account.setConta(profissional.getConta());
      account.setType(BankAccountType.valueOf(profissional.getTipoConta().getNome()));
      account.setContaDv(profissional.getDigitoVerificador());
      account.setDocumentNumber(profissional.getPessoa().getCpf());
      account.setLegalName(profissional.getNomeFavorecido());
      account.save();

      profissional.setIdConta(account.getId());

      try {
        profissionalNegocio.incluirAtualizar(profissional);
      }
      catch (Exception e) {
        return null;
      }
      return account;
    }
    catch (PagarMeException e) {
      return null;
    }

  }

  private BankAccount consultaConta(Long idPessoa) {

    PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

    Profissional profissional = profissionalNegocio.consultarPorPessoa(idPessoa);

    if (profissional.getIdConta() == null) {
      return new BankAccount();
    }

    try {
      BankAccount account = new BankAccount().find(profissional.getIdConta());

      return account;

    }
    catch(PagarMeException e) {
      return new BankAccount();
    }
  }

  private Recipient consultaRecebedor(Long idPessoa) {

    Profissional profissional = profissionalNegocio.consultarPorPessoa(idPessoa);

    PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

    try {
      return new Recipient().find(profissional.getIdRecebedor());
    }
    catch(PagarMeException e) {
      return new Recipient();
    }
  }

}
