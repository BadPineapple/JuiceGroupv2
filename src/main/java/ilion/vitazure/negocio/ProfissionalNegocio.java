package ilion.vitazure.negocio;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.util.StatusEnum;
import ilion.util.Uteis;
import ilion.util.UtilIpRequest;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.enumeradores.ConselhoProfissionalEnum;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.SituacaoAprovacaoProfissionalEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.Especialidade;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.HorarioPossivelAtendimento;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import net.mlw.vlh.ValueList;

@Service
@SuppressWarnings("unchecked")
public class ProfissionalNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private HorarioAtendimentoNegocio horarioNegocio;
	
	@Autowired
	private AgendaNegocio agendaciaNegocio;
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@Autowired
	private EnvioEmailConsulta envioEmailConsulta;
	
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
	
	public List<Profissional> consultarProfissionaisAtivos(Pessoa pessoaSessao) {
		
//		DetachedCriteria subquery = DetachedCriteria.forClass(Profissional.class);
//		subquery.add( Restrictions.eq("avisoFerias", Boolean.TRUE)).add(Restrictions.ge("dataInicioAvisoFerias", Uteis.formatarDataHora(new Date(), "dd/MM/YYYY"))).add(Restrictions.le("dataFimAvisoFerias", Uteis.formatarDataHora(new Date(), "dd/MM/YYYY")));
//		subquery.setProjection(Projections.property("id"));
//		List list =  hibernateUtil.list(subquery);
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.add(Restrictions.eq("situacaoAprovacaoProfissional", SituacaoAprovacaoProfissionalEnum.AUTORIZADO));
		dc.add(Restrictions.eq("dadosCompleto", Boolean.TRUE));
//        if (!list.isEmpty()) {
//        	dc.add(Restrictions.not(Restrictions.in("id", list)));
//		}
        if (pessoaSessao != null && pessoaSessao.getPessoaImportada()) {
          List<Usuario> listUsuarioEmpresa = usuarioNegocio.consultarPorEmpresa(pessoaSessao.getEmpresaImportacao());
          String convenioConsulta = listUsuarioEmpresa.get(0).getConvenio().equals("45") ? "convenio40" : listUsuarioEmpresa.get(0).getConvenio().equals("56") ? "convenio56" :  listUsuarioEmpresa.get(0).getConvenio().equals("67") ? "convenio60" : "ativo";
          dc.add(Restrictions.eq(convenioConsulta, Boolean.TRUE));
          List<Profissional> listProfissionais = (List<Profissional>) hibernateUtil.list(dc);
          listProfissionais.stream().forEach(profissional -> {
        	  if(!listUsuarioEmpresa.get(0).getConvenio().equals("")) {
        		  profissional.setValorConsultaOnline(new BigDecimal(listUsuarioEmpresa.get(0).getConvenio())); 
        		  profissional.setValorConsultaPresencial(new BigDecimal(listUsuarioEmpresa.get(0).getConvenio()));
        	  }
          });
          return listProfissionais;
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
		}else if(profissionalVH.getProfissional().getAvisoFerias()) {
		    validarDataAvisoFerias(profissionalVH);
		}
	}
	
	private void validarDadosProfissionais(ProfissionalVH profissionalVH)  throws Exception{
		if(Uteis.ehNuloOuVazio(profissionalVH.getProfissional().getDocumentoCrpCrm())) {
			throw new ValidacaoException("Documento do (CRP/CRM) n??o informado.");
		}
		if(Uteis.ehNuloOuVazio(profissionalVH.getProfissional().getCadastroEpsi())) {
			throw new ValidacaoException("Cadastro do e-Psi n??o informado.");
		}
		if(Uteis.ehNuloOuVazio(profissionalVH.getProfissional().getDataValidadeEpsi())) {
			throw new ValidacaoException("Data valida????o do e-Psi n??o informado.");
		}
		if(profissionalVH.getProfissional().getTipoProfissional().equals(TipoProfissionalEnum.NAO_INFORMADO)) {
			throw new ValidacaoException("Tipo profissional n??o informado.");
		}
		if(profissionalVH.getEspecialidade().isEmpty()) {
			throw new ValidacaoException("Especialidade n??o informado.");
		}
		if(profissionalVH.getTemasTrabalho().isEmpty()) {
			throw new ValidacaoException("Temas n??o informado.");
		}
		if(!profissionalVH.getProfissional().getAdolescentes() && !profissionalVH.getProfissional().getAdultos() && !profissionalVH.getProfissional().getCasais() && !profissionalVH.getProfissional().getIdosos()) {
			throw new ValidacaoException("Faixa de atendimento ??? obrigat??rio marcar pelo menos uma das op????es.");
		}
		if(profissionalVH.getProfissional().getConselhoProfissional().equals(ConselhoProfissionalEnum.NAO_INFORMADO)) {
			throw new ValidacaoException("Conselho profissional n??o informado.");
		}
		
	}
	
	private void validarFormacao(ProfissionalVH profissionalVH)  throws Exception{
		if(profissionalVH.getFormacaoAcademica().isEmpty()) {
			throw new ValidacaoException("Forma????o n??o informado.");
		}
	}
	private void validarTempoDuracao(ProfissionalVH profissionalVH)  throws Exception{
		if(profissionalVH.getProfissional().getDuracaoAtendimento().equals(DuracaoAtendimentoEnum.NAO_INFORMADO)) {
			throw new ValidacaoException("Dura????o de atendimento n??o informado.");
		}
	}
	
	private void validarDataAvisoFerias(ProfissionalVH profissionalVH)  throws Exception{
		if(Uteis.validarDataInicialMaiorFinal(profissionalVH.getProfissional().getDataInicioAvisoFerias() , profissionalVH.getProfissional().getDataFimAvisoFerias())) {
			throw new ValidacaoException("Data inicial n??o pode ser maior que data final.");
		}
	}
	
	public Boolean dadosProfissionaisCompleto(ProfissionalVH profissionalVH) throws Exception {
		Boolean dadosCompleto = Boolean.TRUE;		
		if(!profissionalVH.getProfissional().getDadosProficionalCompleto() || profissionalVH.getEspecialidade().isEmpty() || profissionalVH.getTemasTrabalho().isEmpty()) {
			profissionalVH.getItensIncompletos().add("Dados Profissional");
			dadosCompleto = Boolean.FALSE;
		}
		if(!profissionalVH.getProfissional().getTempoDuracaoCompleto()) {
			profissionalVH.getItensIncompletos().add("Tempo de dura????o do atendimento");
			dadosCompleto = Boolean.FALSE;
		}
		if(!profissionalVH.getProfissional().getDadosSobreMimCompleto()) {
			profissionalVH.getItensIncompletos().add("Sobre Mim");
			dadosCompleto = Boolean.FALSE;
		}
		if(profissionalVH.getFormacaoAcademica().isEmpty()) {
			profissionalVH.getItensIncompletos().add("Forma????o");
			dadosCompleto = Boolean.FALSE;
		}
		if(profissionalVH.getHorarioAtendimento().isEmpty()) {
			profissionalVH.getItensIncompletos().add("Hor??rio atendimento");
			dadosCompleto = Boolean.FALSE;
		}
		if(!profissionalVH.getProfissional().getDadosValoresConsultaCompleto()) {
			profissionalVH.getItensIncompletos().add("Valores de consulta");
			dadosCompleto = Boolean.FALSE;
		}
		if(profissionalVH.getProfissional().getIdConta() == null || profissionalVH.getProfissional().getIdConta() == 0 ) {
			profissionalVH.getItensIncompletos().add("Dados bancario para recebimento de consultas");
			dadosCompleto = Boolean.FALSE;
		}if(!profissionalVH.getProfissional().getAceiteContrato()) {
			profissionalVH.getItensIncompletos().add("Termo de Ades??o de presta????o de servi??os da Vitazure");
			dadosCompleto = Boolean.FALSE;
		}if(!profissionalVH.getProfissional().getAceiteUsoImagem()) {
			profissionalVH.getItensIncompletos().add("Termo de Uso de Imagem da Vitazure");
			dadosCompleto = Boolean.FALSE;
		}
		profissionalVH.getProfissional().setDadosCompleto(dadosCompleto);
		Profissional obj = new Profissional();
		obj = profissionalVH.getProfissional();
		atualizarSituacaoDadosCompletoProfissional(obj);
        return dadosCompleto;
	}
	
	public void atualizarSituacaoDadosCompletoProfissional(Profissional profissional) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update profissional set dadoscompleto = ").append(profissional.getDadosCompleto());
		sql.append(" where id = ").append(profissional.getId());
		hibernateUtil.updateSQL(sql.toString());
	}
	
	public List<Profissional> consultarProfissionaisFiltro(String palavraChave , String especialista ,  String estado, String cidade , Pessoa pessoaSessao) {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.add(Restrictions.eq("situacaoAprovacaoProfissional", SituacaoAprovacaoProfissionalEnum.AUTORIZADO));
		dc.add(Restrictions.eq("dadosCompleto", Boolean.TRUE));
//		DetachedCriteria subquery = DetachedCriteria.forClass(Profissional.class);
//		subquery.add( Restrictions.eq("avisoFerias", Boolean.TRUE)).add(Restrictions.ge("dataInicioAvisoFerias", Uteis.formatarDataHora(new Date(), "dd/MM/YYY"))).add(Restrictions.le("dataFimAvisoFerias", Uteis.formatarDataHora(new Date(), "dd/MM/YYY")));
//		subquery.setProjection(Projections.property("id"));
//		List list =  hibernateUtil.list(subquery);
		if(palavraChave != null && !palavraChave.equals("undefined")) {
			dc.createAlias("pessoa", "pessoa");
			dc.add(Restrictions.or(Restrictions.ilike("pessoa.nome", "%"+palavraChave+"%") , Restrictions.ilike("biografia", "%"+palavraChave+"%")));
		}
		if(especialista != null && !especialista.equals("undefined")) {
			DetachedCriteria subqueryEspecialidade = DetachedCriteria.forClass(Especialidade.class);
			subqueryEspecialidade.createAlias("profissional", "profissional");
			subqueryEspecialidade.add( Restrictions.eq("especialidade", especialista));
			subqueryEspecialidade.setProjection(Projections.property("profissional.id"));
			List listEspecialidade =  hibernateUtil.list(subqueryEspecialidade);
			if(!listEspecialidade.isEmpty()) {
				dc.add(Restrictions.in("id", listEspecialidade));
			}else {
				dc.add(Restrictions.eq("id", 0L));
			}
		}
		if(estado != null && !estado.equals("undefined")) {
			DetachedCriteria subqueryEstadoCidade = DetachedCriteria.forClass(EnderecoAtendimento.class);
			subqueryEstadoCidade.createAlias("profissional", "profissional");
			subqueryEstadoCidade.add( Restrictions.eq("estado", EstadoEnum.valueOf(estado)));
			if(!cidade.equals("null")) {
				subqueryEstadoCidade.add( Restrictions.ilike("cidade", cidade));
			}
			subqueryEstadoCidade.setProjection(Projections.property("profissional.id"));
			List listEstadoCidade =  hibernateUtil.list(subqueryEstadoCidade);
			if(!listEstadoCidade.isEmpty()) {
				dc.add(Restrictions.in("id", listEstadoCidade));
			}else {
				dc.add(Restrictions.eq("id", 0L));
			}
		}
//        if (!list.isEmpty()) {
//        	dc.add(Restrictions.not(Restrictions.in("id", list)));
//		}
        if (pessoaSessao != null && pessoaSessao.getPessoaImportada()) {
            List<Usuario> listUsuarioEmpresa = usuarioNegocio.consultarPorEmpresa(pessoaSessao.getEmpresaImportacao());
            String convenioConsulta = listUsuarioEmpresa.get(0).getConvenio().equals("45") ? "convenio40" : listUsuarioEmpresa.get(0).getConvenio().equals("56") ? "convenio56" :  listUsuarioEmpresa.get(0).getConvenio().equals("67") ? "convenio60" : "ativo";
            dc.add(Restrictions.eq(convenioConsulta, Boolean.TRUE));
            List<Profissional> listProfissionais = (List<Profissional>) hibernateUtil.list(dc);
            listProfissionais.stream().forEach(profissional -> {
          	  if(!listUsuarioEmpresa.get(0).getConvenio().equals("")) {
          		  profissional.setValorConsultaOnline(new BigDecimal(listUsuarioEmpresa.get(0).getConvenio())); 
          		  profissional.setValorConsultaPresencial(new BigDecimal(listUsuarioEmpresa.get(0).getConvenio()));
          	  }
            });
            return listProfissionais;
  		}
		return (List<Profissional>) hibernateUtil.list(dc);
	}
	
private void validarHorarioDisponivelProfissional(List<HorarioPossivelAtendimento> listaHorarioatendimento, Profissional profissional , Date datavalidar) {
		
	List<Agenda> listHorarioOcupadoProfissional = agendaciaNegocio.consultarAgendaDiaProfissional(profissional, datavalidar);
	List<HorarioPossivelAtendimento> horariosOcupados = listaHorarioatendimento.stream()
	        .filter( o1 -> {
	            return listHorarioOcupadoProfissional.stream()
	                    .map(Agenda::getHoraInicioAgenda)
	                    .anyMatch(i2 -> i2.equals(o1.getHoraPossivelAtendiemnto()));
	        }).collect(Collectors.toList());
				
	listaHorarioatendimento.removeAll(horariosOcupados);
	
}
	
	
	public List<HorarioPossivelAtendimento> consultaHorarioPossivelAtendimento(Profissional profissional, Boolean atendimentoOnline,	Boolean atendimentoPresencial, Date dataConsulta){
		List<HorarioAtendimento> listaHorarioatendimento = new ArrayList<HorarioAtendimento>();
		horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId(),	atendimentoOnline, atendimentoPresencial);
		listaHorarioatendimento.addAll(horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId(),	atendimentoOnline, atendimentoPresencial));
		List<HorarioPossivelAtendimento> listHorarioPossivelAtendimento = new ArrayList<HorarioPossivelAtendimento>();
		Date horaMaximaAgendamento = Uteis.acrescentar(new Date(), Calendar.MINUTE, (Integer.parseInt(profissional.getTempoAntecendencia().getNome())* 60));
		listaHorarioatendimento.stream().filter(a -> a.getDiaSemana().getValue() == dataConsulta.getDay())
				.forEach(horarioAtendimento -> {
					Integer quantidadeMinutosAtendimento = Uteis.diferencaEmMinutos(
							Uteis.converterHoraEmDate(horarioAtendimento.getHoraFim(), "HH:mm"),
							Uteis.converterHoraEmDate(horarioAtendimento.getHoraInicio(), "HH:mm"));
					Integer quantidadePossiveisAtendimento = quantidadeMinutosAtendimento / (Integer.parseInt(profissional.getDuracaoAtendimento().getNome()) + 10);
					int x = 1;
					String horaInicio = horarioAtendimento.getHoraInicio();
					String horaFinal = "";
					if (!horarioAtendimento.getHoraInicio().equals("")) {
						HorarioPossivelAtendimento horarioPossivelAtendimentoInicial = new HorarioPossivelAtendimento();
						horarioPossivelAtendimentoInicial.setHoraPossivelAtendiemnto(horaInicio);
						horarioPossivelAtendimentoInicial.setDiaSemanaEnum(horarioAtendimento.getDiaSemana());
						horarioPossivelAtendimentoInicial.setCodigoProfissional(profissional.getId());
						if (horarioAtendimento.getEnderecoAtendimento() != null	&& horarioAtendimento.getEnderecoAtendimento().getId() != null) {
							horarioPossivelAtendimentoInicial.setEnderecoatendimento(horarioAtendimento
									.getEnderecoAtendimento().getLogradouro().concat(" - ")
									.concat(horarioAtendimento.getEnderecoAtendimento().getComplemento()).concat(" - ")
									.concat(horarioAtendimento.getEnderecoAtendimento().getBairro().concat(" - ")
											.concat(horarioAtendimento.getEnderecoAtendimento().getCidade())
											.concat(" - ").concat(horarioAtendimento.getEnderecoAtendimento()
													.getEstado().getNome())));
							horarioPossivelAtendimentoInicial.setLinkGoogleMaps(horarioAtendimento.getEnderecoAtendimento().getLinkGoogleMaps());
						}
						if(Uteis.validarDataInicialMaiorFinalComHora(horaInicio,dataConsulta, horaMaximaAgendamento) || !Uteis.isHojeIndependenteDaHora(dataConsulta)) {
							listHorarioPossivelAtendimento.add(horarioPossivelAtendimentoInicial);
						}
						while (x <= quantidadePossiveisAtendimento) {
							HorarioPossivelAtendimento horarioPossivelAtendimento = new HorarioPossivelAtendimento();
							horarioPossivelAtendimento.setHoraPossivelAtendiemnto(Uteis.calculodeHoraSemIntervalo(horaInicio, 1,
											(Integer.parseInt(profissional.getDuracaoAtendimento().getNome()) + 10)));
							horarioPossivelAtendimento.setDiaSemanaEnum(horarioAtendimento.getDiaSemana());
							horarioPossivelAtendimento.setCodigoProfissional(profissional.getId());
							if (horarioAtendimento.getEnderecoAtendimento() != null
									&& horarioAtendimento.getEnderecoAtendimento().getId() != null) {
								horarioPossivelAtendimento.setEnderecoatendimento(horarioAtendimento
										.getEnderecoAtendimento().getLogradouro().concat(" - ")
										.concat(horarioAtendimento.getEnderecoAtendimento().getComplemento())
										.concat(" - ").concat(horarioAtendimento.getEnderecoAtendimento().getBairro()));
								horarioPossivelAtendimento.setLinkGoogleMaps(
										horarioAtendimento.getEnderecoAtendimento().getLinkGoogleMaps());
							}
							if(Uteis.validarDataInicialMaiorFinalComHora(horaInicio,dataConsulta, horaMaximaAgendamento) || !Uteis.isHojeIndependenteDaHora(dataConsulta)) {
							 listHorarioPossivelAtendimento.add(horarioPossivelAtendimento);
							}
							horaInicio = horarioPossivelAtendimento.getHoraPossivelAtendiemnto();
							x++;
						}
					}
				});
		validarHorarioDisponivelProfissional(listHorarioPossivelAtendimento,profissional , dataConsulta);
		return listHorarioPossivelAtendimento;
	}
	
	 public Profissional consultarDataDisponivelProfissional(Profissional profissional , Boolean atendimentoOnline , Boolean atendimentoPresencial) {
	   	  
	   	  List<Date> lista = new ArrayList<Date>();
	   	  
	   	  int diasIncrementado = 0;
	   	  while (diasIncrementado  < 60){
	   		  lista.add(Uteis.acrescentar(new Date(), Calendar.DATE, diasIncrementado));
	   		  diasIncrementado++;
	   	  }
	   		  List<HorarioAtendimento> listaHorarioatendimento = horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId() , atendimentoOnline , atendimentoPresencial);
	   		  List<Date> datasPossivelAgendamento = lista.stream()
	   				  .filter( o1 -> {
	   					  return listaHorarioatendimento.stream()
	   							  .map(HorarioAtendimento::getDiaSemana)
	   							  .anyMatch(i2 -> i2.getValue() == o1.getDay());
	   				  }).collect(Collectors.toList());
	   		  profissional.getDatasPossivelAgendamento().addAll(datasPossivelAgendamento);
	   	  
	   		  return profissional;
	   	  
	     }
	 
	 public Profissional consultaPerfilCompletoPorId(Long idProfissional , Pessoa pessoaSessao) {
			DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
			dc.add(Restrictions.eq("id", idProfissional));
			Profissional profissional = (Profissional) hibernateUtil.consultarUniqueResult(dc);
			if (profissional == null) {
				return new Profissional();
			}
			if (pessoaSessao != null && pessoaSessao.getPessoaImportada()) {
	            List<Usuario> listUsuarioEmpresa = usuarioNegocio.consultarPorEmpresa(pessoaSessao.getEmpresaImportacao());
	          	  if(!listUsuarioEmpresa.get(0).getConvenio().equals("")) {
	          		  profissional.setValorConsultaOnline(new BigDecimal(listUsuarioEmpresa.get(0).getConvenio())); 
	          		  profissional.setValorConsultaPresencial(new BigDecimal(listUsuarioEmpresa.get(0).getConvenio()));
	          	  }
	  		}
            
			return profissional;
		}
	 
		public void enviarAlertaPerfilCompleto(Pessoa pessoaSessao) throws Exception{
			envioEmailConsulta.enviarAlertaPerfilCompleto(pessoaSessao);
		}
		
		public void atualizarSituacaoFirstTimeProfissional(Profissional profissional) {
			StringBuilder sql = new StringBuilder();
			sql.append(" update profissional set FirstTime = ").append(profissional.getFirstTime());
			sql.append(" where id = ").append(profissional.getId());
			hibernateUtil.updateSQL(sql.toString());
		}
}
