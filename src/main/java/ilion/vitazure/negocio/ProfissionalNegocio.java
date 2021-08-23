package ilion.vitazure.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.util.StatusEnum;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import net.mlw.vlh.ValueList;

@Service
@SuppressWarnings("unchecked")
public class ProfissionalNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public Profissional consultarPorPessoa(Long idPessoa) {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.createAlias("pessoa", "p");
		dc.add(Restrictions.eq("p.id", idPessoa));
		Profissional profissional = (Profissional) hibernateUtil.consultarUniqueResult(dc);
		if (profissional == null) {
			return new Profissional();
		}
		return profissional;
	}
	
	@Transactional
	public Profissional incluirAtualizar(Profissional profissional) throws Exception{
		if (profissional.getId() == null || profissional.getId() == 0) {
			profissional = (Profissional) hibernateUtil.save(profissional);
		} else {
			hibernateUtil.update(profissional);
		}
		return profissional;
	}
	
	public List<Profissional> consultarProfissionais() {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		return (List<Profissional>) hibernateUtil.list(dc);
	}
	
	public Profissional consultarPorId(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.add(Restrictions.eq("id", idProfissional));
		Profissional profissional = (Profissional) hibernateUtil.consultarUniqueResult(dc);
		if (profissional == null) {
			return new Profissional();
		}
		return profissional;
	}
	
	public ValueList buscar(VLHForm vlhForm, ValueListInfo valueListInfo , Usuario usuarioSessao) {

		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.createAlias("pessoa", "pessoa");
		if (!Uteis.ehNuloOuVazio(vlhForm.getPalavraChave())) {
			Disjunction disjunction = Restrictions.disjunction();
			List<String> condicoes = PalavrasChaveCondicoes.nova().comPalavrasChave(vlhForm.getPalavraChave()).gerar();
			for (String condicao : condicoes) {
				disjunction.add( Restrictions.ilike("pessoa.nome", condicao));
				disjunction.add( Restrictions.ilike("pessoa.email", condicao));
				disjunction.add( Restrictions.ilike("plano", condicao));
			}
			Long id = Uteis.converterLong(vlhForm.getPalavraChave());

			if (id != null) {
				disjunction.add(Restrictions.eq("id", id));
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
	
	public List<Profissional> consultarProfissionaisAtivos() {
		
		DetachedCriteria subquery = DetachedCriteria.forClass(Profissional.class);
		subquery.add( Restrictions.eq("avisoFerias", Boolean.TRUE)).add(Restrictions.ge("dataInicioAvisoFerias", Uteis.formatarDataHora(new Date(), "dd/MM/YYY")))
		.add(Restrictions.le("dataFimAvisoFerias", Uteis.formatarDataHora(new Date(), "dd/MM/YYY")));
		subquery.setProjection(Projections.property("id"));
		List list =  hibernateUtil.list(subquery);
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.add(Restrictions.eq("ativo", Boolean.TRUE));
        if (!list.isEmpty()) {
        	dc.add(Restrictions.not(Restrictions.in("id", list)));
		}
		return (List<Profissional>) hibernateUtil.list(dc);
	}
	
	public void validarDados(ProfissionalVH profissionalVH)  throws Exception{
		if(profissionalVH.getMenuValidar().equals("dadosProfissional")) {
			validarDadosProfissionais(profissionalVH);
		}else if(profissionalVH.getMenuValidar().equals("formacao")) {
			validarFormacao(profissionalVH);
		}else if(profissionalVH.getMenuValidar().equals("tempoDuracao")) {
			validarTempoDuracao(profissionalVH);
		}
	}
	
	private void validarDadosProfissionais(ProfissionalVH profissionalVH)  throws Exception{
		if(Uteis.ehNuloOuVazio(profissionalVH.getProfissional().getDocumentoCrpCrm())) {
			throw new ValidacaoException("Documento do (CRP/CRM) não informado.");
		}
		if(Uteis.ehNuloOuVazio(profissionalVH.getProfissional().getCadastroEpsi())) {
			throw new ValidacaoException("Cadastro do e-Psi não informado.");
		}
		if(profissionalVH.getProfissional().getTipoProfissional().equals(TipoProfissionalEnum.NAO_INFORMADO)) {
			throw new ValidacaoException("Tipo profissional não informado.");
		}
		if(profissionalVH.getEspecialidade().isEmpty()) {
			throw new ValidacaoException("Especialidade não informado.");
		}
		if(profissionalVH.getTemasTrabalho().isEmpty()) {
			throw new ValidacaoException("Temas não informado.");
		}
		if(!profissionalVH.getProfissional().getAdolescentes() && profissionalVH.getProfissional().getAdultos() && profissionalVH.getProfissional().getCasais() && profissionalVH.getProfissional().getIdosos()) {
			throw new ValidacaoException("Faixa de atendimento – obrigatório marcar pelo menos uma das opções.");
		}
		
	}
	
	private void validarFormacao(ProfissionalVH profissionalVH)  throws Exception{
		if(profissionalVH.getFormacaoAcademica().isEmpty()) {
			throw new ValidacaoException("Formação não informado.");
		}
	}
	private void validarTempoDuracao(ProfissionalVH profissionalVH)  throws Exception{
		if(profissionalVH.getProfissional().getDuracaoAtendimento().equals(DuracaoAtendimentoEnum.NAO_INFORMADO)) {
			throw new ValidacaoException("Duração de atendimento não informado.");
		}
	}
	
}
