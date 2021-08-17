package ilion.vitazure.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.Usuario;
import ilion.me.pagar.BankAccountType;
import ilion.me.pagar.RecipientStatus;
import ilion.me.pagar.model.BankAccount;
import ilion.me.pagar.model.Item;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.PagarMeError;
import ilion.me.pagar.model.PagarMeException;
import ilion.me.pagar.model.Plan;
import ilion.me.pagar.model.Postback;
import ilion.me.pagar.model.Recipient;
import ilion.me.pagar.model.SplitRule;
import ilion.me.pagar.model.Subscription;
import ilion.me.pagar.model.Transaction;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.model.PagamentoPagarMe;
import ilion.vitazure.model.Profissional;
import net.mlw.vlh.ValueList;

@Service
@SuppressWarnings("unchecked")
public class PagarMeNegocio {

  @Autowired
  private PropNegocio propNegocio;

  @Autowired
  private ProfissionalNegocio profissionalNegocio;
  
  @Autowired
  private HibernateUtil hibernateUtil;


  public String cadastraRecebedor (Profissional profissional) throws Exception{
    
	  validarCampos(profissional);  
	  
	try {
         BankAccount account = cadastraConta(profissional);

    if(account == null) {
      return "Erro ao cadastrar conta bancária";
    }


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
      return tratamentoErroPagarMe(e.getErrors());
    }

  }

  
  private void validarCampos(Profissional profissional) throws Exception {
	  
	  if (profissional.getAgencia().trim().equals("")) {
		  throw new Exception("Agência não informada.");
	  }else if (profissional.getConta().trim().equals("")) {
		  throw new Exception("Conta não informada.");
	  }else if (profissional.getDigitoVerificador().trim().equals("")) {
		  throw new Exception("Digito Verificador não informada.");
	  }else if (profissional.getNomeFavorecido().trim().equals("")) {
		  throw new Exception("Nome Valorecido não informada.");
	  }else if (profissional.getBanco() == BancoEnum.NAO_INFORMADO) {
	      throw new Exception("Banco não informada.");
      }else if (profissional.getTipoConta() == TipoContaEnum.NAO_INFORMADO) {
	      throw new Exception("Tipo Conta não informada.");
      }
	  
  }
  
  private String tratamentoErroPagarMe(Collection<PagarMeError> erros) {
	StringBuilder ErroTransacao = new StringBuilder();
	ErroTransacao.append("Erro ao salvar cadastro ");
	erros.stream().forEach(pagarMeError -> ErroTransacao.append(pagarMeError.getMessage()).append(" ").append(pagarMeError.getParameterName()));
	if(ErroTransacao.toString().contains("conta")) {
		return "Problema com os dados da conta";
	}else if(ErroTransacao.toString().contains("agencia")) {
		return "Problema com os dados da agência";
	}
	return ErroTransacao.toString();
	
  }
  
  
  private BankAccount cadastraConta(Profissional profissional) throws PagarMeException {

    PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

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
	    if (profissional.getIdRecebedor() != null || profissional.getIdRecebedor().equals("")) {
	    	return new Recipient();
		}else {
			return new Recipient().find(profissional.getIdRecebedor());
		}
    }
    catch(PagarMeException e) {
      return new Recipient();
    }
  }

  public String capturaTransacao(String transactionId) {
    PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
    String itemId = "0";

    try {

      Transaction transaction = new Transaction().find(transactionId);

      Collection<Item> items = transaction.getItems();

      for (Item item : items) {
        itemId = item.getId();
      }

      SplitRule rule = new SplitRule();

      //TODO: id 1: assinatura semestral e anual, id 2: consulta particular, id 3: assinatura recorrente

      if(itemId.equals("2")) {
        //TODO: adicionar o id do recebedor que esta no banco

        rule.setRecipientId("ID DO BANCO");
        rule.setPercentage(100);

        Collection<SplitRule> rules = new ArrayList<>();

        rules.add(rule);
        transaction.setSplitRules(rules);
      }

      transaction.capture(transaction.getAmount());

      return "Captura com sucesso";

    }
    catch (PagarMeException e) {
      return "erro ao capturar a transação: " + e.getMessage();
    }

  }

  public String criaPlano(Integer valor, Integer dias) {
    try {
      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

      Plan plan = new Plan();
      plan.setAmount(valor * 100);
      plan.setDays(dias);

      Collection<Plan> plans = consultaPlanos();

      for (Plan planTemp : plans) {
        if (planTemp.getAmount() == plan.getAmount() && planTemp.getDays() == plan.getDays()) {
          return "Este plano ja exite";
        }
      }
      plan.save();

      return "Plano criado com sucesso";
    }
    catch (PagarMeException e) {
      return "Erro ao criar plano: " + e.getMessage();
    }
  }

  public Collection<Plan> consultaPlanos() {
    try {
      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

      Collection<Plan> plans = new Plan().findCollection(10,1);
      return plans;
    }
    catch (PagarMeException e) {
      return null;
    }
  }

  public String alteraPlano(String planoId, Integer valor, Integer dias) {
    try {
      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
      Plan plan = new Plan().find(planoId);

      if (plan == null) {
        return "Plano não encontrado";
      }

      plan.setAmount(valor * 100);
      plan.setDays(dias);
      plan.save();

      return "Plano atualizado com sucesso";
    }
    catch(PagarMeException e) {
      return "Erro ao atualizar o plano: " + e.getMessage();
    }
  }

  public String criaAssinatura(Transaction transaction) {
    try{
      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

      Collection<Plan> plans = consultaPlanos();

      String planId = null;

      for (Plan planTemp : plans) {
        if(planTemp.getAmount() == transaction.getAmount()) {
          planId = planTemp.getId();
          continue;
        }
      }

      if (planId == null) {
        return "Não foi encontrado o plano";
      }

      Subscription subscription = new Subscription();
      subscription.setCreditCardSubscriptionWithCardId(planId, transaction.getCard().getId(), transaction.getCustomer());
      subscription.setPostbackUrl("www.vitazure.com.br/api/v1/postback");
      subscription.save();

      /* TODO: trazer o perfil do profissional para salvar o id da Assinatura e o plano

      profissional.setIdAssinatura(subscription.getId);
      profissional.setIdAssinatura(subscription.getPlanId);

      */

      return "Assinatura feita com sucesso";
    }
    catch (PagarMeException e){
      return "Erro ao realizar a assinatura: " + e.getMessage();
    }

  }

  public String alteraAssinatura(String subscriptionId) {
    try {
      PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));

      Subscription subscription = new Subscription().find(subscriptionId);
      /*
        TODO: Só é possível alterar o plano e o pagamento
        subscription.setCardId();
        subscription.setPlanId();
      */
    }
    catch(PagarMeException e) {
      return "Erro ao atualizar a assinatura: " + e.getMessage();
    }

    return "Assinatura atualizada com sucesso";
  }
  
  
  public void salvarRetorno(Postback post) {
	  hibernateUtil.save(post);
  }
  
  @Transactional
  public void salvarPagamentoPagarMe(PagamentoPagarMe pagamentoPagarMe) {
	  hibernateUtil.save(pagamentoPagarMe);
  }
  
  public List<PagamentoPagarMe> consultarPagamentoPagarMe(Long codigoPessoa , Boolean profissional){
		
		List<PagamentoPagarMe> listPagamentoPagarMe = new ArrayList<PagamentoPagarMe>();
		DetachedCriteria dc = DetachedCriteria.forClass(PagamentoPagarMe.class);
		if (profissional) {
			Profissional prof = profissionalNegocio.consultarPorPessoa(codigoPessoa);
			dc.add(Restrictions.eq("idProfissional", prof.getId()));
			dc.add(Restrictions.isNotNull("agenda"));
		}else {
			dc.add(Restrictions.eq("idPaciente", codigoPessoa));	
		}
		listPagamentoPagarMe = (List<PagamentoPagarMe>) hibernateUtil.list(dc);
		return listPagamentoPagarMe;
	}
  
  public ValueList buscar(VLHForm vlhForm, ValueListInfo valueListInfo , Usuario usuarioSessao) {

		DetachedCriteria dc = DetachedCriteria.forClass(PagamentoPagarMe.class);
		if (!Uteis.ehNuloOuVazio(vlhForm.getPalavraChave())) {
			Disjunction disjunction = Restrictions.disjunction();
			List<String> condicoes = PalavrasChaveCondicoes.nova().comPalavrasChave(vlhForm.getPalavraChave()).gerar();
			for (String condicao : condicoes) {
				disjunction.add( Restrictions.ilike("profissional", condicao));
				disjunction.add( Restrictions.ilike("planoVitazureSelecionado", condicao));
				disjunction.add( Restrictions.ilike("status", condicao));
			}
			Long id = Uteis.converterLong(vlhForm.getPalavraChave());
			if (id != null) {
				disjunction.add(Restrictions.eq("id", id));
				disjunction.add(Restrictions.eq("idPaciente", id));
				disjunction.add(Restrictions.eq("agenda", id));
			}
			Integer idTransacao = Uteis.converterInteger(vlhForm.getPalavraChave());
			if (idTransacao != null) {
				disjunction.add(Restrictions.eq("idTransacao", idTransacao));
			}
			dc.add(disjunction);
		}

		StatusEnum statusEnum = StatusEnum.fromString(vlhForm.getStatus());

		if (statusEnum != null) {
			dc.add(Restrictions.eq("status", statusEnum));
		}
		
		ValueList notificacaos = hibernateUtil.consultarValueList(dc, org.hibernate.criterion.Order.desc("id"), valueListInfo);

		return notificacaos;

	}
  
  
  
}
