package ilion.contato.negocio;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.contato.controller.ContatoVH;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailNegocio;
import ilion.email.negocio.EmailSenderFactory;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.exceptions.EhNuloOuVazioException;
import ilion.util.exceptions.EmailInvalidoException;
import ilion.util.exceptions.NomeNuloException;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
@SuppressWarnings("unchecked")
public class ContatoNegocio {

	static Logger logger = Logger.getLogger(ContatoNegocio.class);

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private EmailNegocio emailNegocio;

	@Autowired
	private PropNegocio propNegocio;

	@Autowired
	private ArquivoUteis arquivoUteis;

	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}

	@Transactional
	public Object inserir(Object object) {
		return hibernateUtil.save(object);
	}

	@Transactional
	public void atualizar(Object object) {
		hibernateUtil.update(object);
	}

	@Transactional
	public void excluir(Object object) {
		hibernateUtil.delete(object);
	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public Contato incluirAtualizar(Contato contato) throws Exception {
		List<Long> idsLong = contato.getIdsGrupos();

		if (contato.getId() == null) {
			contato = (Contato) hibernateUtil.save(contato);
		} else {
			hibernateUtil.update(contato);
		}

		inserirContatoXGrupos(contato, idsLong);

		return contato;
	}

	public void inserirContatoXGrupos(Contato contato, List<Long> idsGrupoLong) {
		hibernateUtil.createQueryUpdate(
				" delete from " + ContatoXGrupo.class.getName() + " where contato.id='" + contato.getId() + "' ");

		if (idsGrupoLong != null) {
			for (Iterator iterator = idsGrupoLong.iterator(); iterator.hasNext();) {
				Long idGrupo = null;

				Object idGrupoObj = iterator.next();
				if (idGrupoObj instanceof String) {
					idGrupo = Uteis.converterLong((String) idGrupoObj);
				} else if (idGrupoObj instanceof Number) {
					idGrupo = ((Number) idGrupoObj).longValue();
				}

				ContatoXGrupo contatoXGrupo = new ContatoXGrupo();
				contatoXGrupo.setContato(contato);
				contatoXGrupo.setGrupo(new ContatoGrupo(idGrupo));

				hibernateUtil.save(contatoXGrupo);
			}
		}
	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public void excluirContato(Contato contato) throws Exception {

		contato = (Contato) consultar(Contato.class, contato.getId());

		List<ContatoXGrupo> contatoGrupos = listarContatoXGrupo(contato);

		for (ContatoXGrupo contatoXGrupo : contatoGrupos) {
			hibernateUtil.delete(contatoXGrupo);
		}

		hibernateUtil.delete(contato);
	}

	public ValueList busca(VLHForm vlhForm, Boolean permissaoEmail, String colunas, ValueListInfo valueListInfo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contato.class, "_");

		if ("newsletter-bloqueada".equals(vlhForm.getGrupo())) {
			dc.add(Restrictions.eq("_.permissaoEmail", false));
		} else {
			if (permissaoEmail != null) {
				dc.add(Restrictions.eq("_.permissaoEmail", permissaoEmail));
			}
		}

		if (vlhForm.getLetraInicial() != null && vlhForm.getLetraInicial().trim().length() != 0) {
			dc.add(Restrictions.or(Restrictions.like("_.nome", vlhForm.getLetraInicial().toLowerCase() + "%"),
					Restrictions.like("_.nome", vlhForm.getLetraInicial().toUpperCase() + "%")));
		} else {
			if (vlhForm.getPalavraChave() != null && vlhForm.getPalavraChave().trim().length() != 0) {
				String like = "%" + vlhForm.getPalavraChave() + "%";
				Criterion c = Restrictions.like("_.nome", like);
				c = Restrictions.or(c, Restrictions.like("_.empresa", like));
				c = Restrictions.or(c, Restrictions.like("_.cidade", like));
				c = Restrictions.or(c, Restrictions.like("_.estado", like));
				c = Restrictions.or(c, Restrictions.like("_.email", like));
				c = Restrictions.or(c, Restrictions.like("_.setor", like));
				c = Restrictions.or(c, Restrictions.like("_.endereco", like));

				dc.add(c);
			}

//			if (vlhForm.getDataInicio() != null && vlhForm.getDataFim() != null) {
//				Criterion c = Restrictions.between("dataCriacao", vlhForm.getDataInicio(), vlhForm.getDataFim());
//				dc.add(c);
//			} else if (vlhForm.getDataInicio() != null) {
//				Criterion dateGreaterOrEqualThen = Restrictions.ge("dataCriacao", vlhForm.getDataInicio());
//				dc.add(dateGreaterOrEqualThen);
//			} else if (vlhForm.getDataFim() != null) {
//				Criterion dateLessOrEqualThen = Restrictions.le("dataCriacao", vlhForm.getDataFim());
//				dc.add(dateLessOrEqualThen);
//			}

			if (vlhForm.getIdGrupoContato() != null) {
				DetachedCriteria dcSubQuery = DetachedCriteria.forClass(ContatoXGrupo.class);
				dcSubQuery.createAlias("contato", "c");
				dcSubQuery.createAlias("grupo", "t");
				dcSubQuery.add(Restrictions.eq("t.id", vlhForm.getIdGrupoContato()));

				dcSubQuery.setProjection(Projections.property("c.id"));

				dc.add(Subqueries.propertyIn("_.id", dcSubQuery));
			}
		}

		return hibernateUtil.consultarValueList(dc, Order.asc("_.nome"), colunas, valueListInfo);
	}

	public void validarContato(Contato contato, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome n�o pode ser vazio!");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "email", "", "E-mail n�o pode ser vazio!");

		if (!Uteis.ehEmailValido(contato.getEmail())) {
			bindingResult.rejectValue("email", "", "E-mail inv�lido, por favor digite um e-mail v�lido!");
		} else if (existeContatoEmail(contato)) {
			bindingResult.rejectValue("email", "", "J� existe um contato com este e-mail.");
		}
	}

	public Boolean existeContatoEmail(Contato contato) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contato.class);
		dc.add(Restrictions.eq("email", contato.getEmail()));

		if (contato.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", contato.getId())));
		}

		return hibernateUtil.possuiRegistros(dc);
	}

	public Contato consultarContatoEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contato.class);
		dc.add(Restrictions.eq("email", email));

		return (Contato) hibernateUtil.uniqueResult(dc);
	}

	public List<ContatoXGrupo> listarContatoXGrupo(Contato contato) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("contato", "c");
		dc.add(Restrictions.eq("c.id", contato.getId()));

		return (List<ContatoXGrupo>) hibernateUtil.list(dc);
	}

	public List<Long> listarIdsGrupos(Contato contato) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("contato", "c");
		dc.add(Restrictions.eq("c.id", contato.getId()));

		dc.createAlias("grupo", "t");
		dc.setProjection(Projections.property("t.id"));

		return (List<Long>) hibernateUtil.list(dc);
	}

	public List<Contato> listarContatosPorGrupo(ContatoGrupo grupoContato, Boolean permissaoEmail) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("contato", "c");
		dc.createAlias("grupo", "t");

		if (permissaoEmail != null) {
			dc.add(Restrictions.eq("c.permissaoEmail", permissaoEmail));
		}

		dc.add(Restrictions.eq("t.id", grupoContato.getId()));
		dc.addOrder(Order.asc("c.nome"));

		dc.setProjection(Projections.property("contato"));

		return (List<Contato>) hibernateUtil.list(dc);
	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public void excluirContatosPorGrupo(ContatoGrupo contatoGrupo) throws Exception {

		List<Contato> contatos = listarContatosPorGrupo(contatoGrupo, null);

		for (Contato contato : contatos) {
			try {

				excluirContato(contato);

			} catch (Exception e) {
				logger.error("Erro ao excluir contato nome: " + contato.getNome() + ", erro: " + e.getMessage());
			}
		}
	}

	public List<Map<String, Object>> listarContatosGrupoEstadoCidade(Integer maxResults, Integer firstResult,
			ContatoGrupo grupoContato, String estado, String cidade, Boolean somentePermissaoEmail) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);

		dc.createAlias("contato", "c");
		dc.createAlias("grupo", "t");

		if (somentePermissaoEmail != null && somentePermissaoEmail) {
			dc.add(Restrictions.eq("c.permissaoEmail", true));
		}

		if (grupoContato != null && grupoContato.getId() != null) {
			dc.add(Restrictions.eq("t.id", grupoContato.getId()));
		}

		if (estado != null && estado.length() != 0) {
			dc.add(Restrictions.eq("c.estado", estado));
		}

		if (cidade != null && cidade.length() != 0) {
			dc.add(Restrictions.eq("c.cidade", cidade));
		}

		dc.addOrder(Order.asc("c.nome"));

		dc.setProjection(Projections.property("contato"));

		List<Map<String, Object>> contatosMap = hibernateUtil.listar(dc, maxResults, firstResult,
				"c.nome;c.email;c.empresa;");
		for (Map<String, Object> map : contatosMap) {
			String nome = (String) map.get("c.nome");
			String email = (String) map.get("c.email");
			String empresa = (String) map.get("c.empresa");

			map.put("nome", nome);
			map.put("email", email);
			map.put("empresa", empresa);
		}

		return contatosMap;
	}

	public List<Map<String, Object>> listarContatosGrupo(Integer maxResults, Integer firstResult,
			ContatoGrupo grupoContato, Boolean permissaoEmail) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);

		dc.createAlias("contato", "c");

		if (permissaoEmail != null) {
			dc.add(Restrictions.eq("c.permissaoEmail", permissaoEmail));
		}

		dc.createAlias("grupo", "t");
		if (grupoContato != null && grupoContato.getId() != null) {
			dc.add(Restrictions.eq("t.id", grupoContato.getId()));
		}

		dc.addOrder(Order.asc("c.nome"));

		dc.setProjection(Projections.property("contato"));

		List<Map<String, Object>> contatosMap = hibernateUtil.listar(dc, maxResults, firstResult,
				"c.nome;c.email;c.telefone;");
		for (Map<String, Object> map : contatosMap) {
			String nome = (String) map.get("c.nome");
			String email = (String) map.get("c.email");
			String telefone = (String) map.get("c.telefone");

			map.put("nome", nome);
			map.put("email", email);
			map.put("telefone", telefone);
		}

		return contatosMap;
	}

	public Integer consultarQtdContatosGrupoEstadoCidade(ContatoGrupo grupoContato, String estado, String cidade,
			Boolean somentePermissaoEmail) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);

		dc.createAlias("contato", "c");
		dc.createAlias("grupo", "t");

		if (somentePermissaoEmail != null && somentePermissaoEmail) {
			dc.add(Restrictions.eq("c.permissaoEmail", true));
		}

		if (grupoContato != null && grupoContato.getId() != null) {
			dc.add(Restrictions.eq("t.id", grupoContato.getId()));
		}

		if (estado != null && estado.length() != 0) {
			dc.add(Restrictions.eq("c.estado", estado));
		}

		if (cidade != null && cidade.length() != 0) {
			dc.add(Restrictions.eq("c.cidade", cidade));
		}

		return hibernateUtil.consultarQtd(dc).intValue();
	}

	public List<Map<String, Object>> listarTodosContatos(Integer maxResults, Integer firstResult,
			Boolean permissaoEmail, String colunas) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contato.class);

		if (permissaoEmail != null) {
			dc.add(Restrictions.eq("permissaoEmail", permissaoEmail));
		}

		dc.addOrder(Order.asc("nome"));

		return hibernateUtil.listar(dc, maxResults, firstResult, colunas);
	}

	public Integer consultarQtdTotalContatosNewsletter() {
		DetachedCriteria dc = DetachedCriteria.forClass(Contato.class);
		dc.add(Restrictions.eq("permissaoEmail", true));

		return hibernateUtil.consultarQtd(dc).intValue();
	}

	// public List<Cidade> listarCidades(String estado) {
	// DetachedCriteria dc = DetachedCriteria.forClass(Cidade.class);
	// dc.add(Restrictions.eq("uf", estado));
	// dc.addOrder(Order.asc("nome"));
	//
	// return (List<Cidade>) hibernateUtil.list(dc);
	// }

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public ContatoGrupo consultarGrupoContato(String grupo) {
		ContatoGrupo grupoContato = new ContatoGrupo();
		grupoContato.setNome(grupo);

		DetachedCriteria dc = DetachedCriteria.forClass(ContatoGrupo.class);
		dc.add(Restrictions.eq("nome", grupoContato.getNome()));

		grupoContato = (ContatoGrupo) hibernateUtil.uniqueResult(dc);

		if (grupoContato == null) {
			grupoContato = new ContatoGrupo();
			grupoContato.setNome(grupo);

			grupoContato = (ContatoGrupo) hibernateUtil.save(grupoContato);
		}

		return grupoContato;
	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public Contato cadastraContatoSite(ContatoVH contatoVH) throws Exception {

		if (Uteis.ehNuloOuVazio(contatoVH.getNome())) {
			throw new RuntimeException("Nome deve ser preenchido.");
		}

		if (!Uteis.ehEmailValido(contatoVH.getEmail())) {
			throw new RuntimeException("E-mail deve ser preenchido e válido.");
		}

		Contato contato = new Contato();

		String email = contatoVH.getEmail();

		contato.setEmail(email);

		contato = consultarContatoEmail(email);

		if (contato == null) {
			contato = new Contato();
		}

		contato.setNome(contatoVH.getNome());
		contato.setEmail(email);

		// if( ! Uteis.ehNuloOuVazio(contatoVH.getDdd()) ) {
		// contato.setDdd(contatoVH.getDdd());
		// }
		if (!Uteis.ehNuloOuVazio(contatoVH.getEmpresa())) {
			contato.setEmpresa(contatoVH.getEmpresa());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getTelefone())) {
			contato.setTelefone(contatoVH.getTelefone());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getCelular())) {
			contato.setTelefone(contatoVH.getCelular());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getDataNascimento())) {
			contato.setDataNascimentoString(contatoVH.getDataNascimento());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getCidade())) {
			contato.setCidade(contatoVH.getCidade());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getEndereco())) {
			contato.setEstado(contatoVH.getEndereco());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getEstado())) {
			contato.setEstado(contatoVH.getEstado());
		}
		if (!Uteis.ehNuloOuVazio(contatoVH.getAssunto())) {
			contato.setAssunto(contatoVH.getAssunto());
		}

		contato.setMensagem(contatoVH.getMensagem());
		contato.setObs(contatoVH.getMensagem());

		contato.setCadastradoPor("Web Site");

		contato.setPermissaoEmail(contatoVH.getPermissaoEmail());

		if (contato.getId() == null) {
			contato.setDataCriacao(new Date());
			contato = (Contato) hibernateUtil.save(contato);
		} else {
			hibernateUtil.update(contato);
		}

		vincularContatoAoGrupo(contato, contatoVH.getGrupo());

		adicionarContatoComentario(contato, contatoVH);

		enviarFormularioContatoEmail(contato, contatoVH);

		return contato;
	}

	private void adicionarContatoComentario(Contato contato, ContatoVH contatoVH) {

		if (contatoVH.getCadastrarComentario() == null || !contatoVH.getCadastrarComentario()) {
			return;
		}

		String arquivo = null;
		if (contatoVH.getArquivos() != null && !contatoVH.getArquivos().isEmpty()) {

			ArquivoBase64VH arquivoBase64VH = contatoVH.getArquivos().get(0);

			String pathFile = getPathCurriculo() + "/contato-" + contato.getId() + "-" + UUID.randomUUID().toString()
					+ "." + arquivoBase64VH.getExtensao();

			arquivoUteis.decodeBase64ToFile(arquivoBase64VH.getBase64File(), pathFile);

		}

		ContatoComentario cc = new ContatoComentario();
		cc.setContato(contato);
		cc.setGrupo(contatoVH.getGrupo());
		cc.setComentario(contatoVH.getMensagem());
		cc.setArquivo(arquivo);
		cc.setData(new Date());

		hibernateUtil.save(cc);

	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public void cadastrarFretamento(ContatoVH contatoVH) throws Exception {
		
		contatoVH.setGrupo("Fretamento");
		
		contatoVH.setEnviarEmail(false);
		
		Contato contato = cadastraContatoSite(contatoVH);
		
		String emailsTo = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);

		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");

		if (emailsLista == null) {
			logger.error("nenhum email na propriedade: " + PropEnum.EMAILS_CONTATO);
			return;
		}

		String assunto = "[SITE] Fretamento - " + propNegocio.findValueById(PropEnum.NOME_EMPRESA);

		StringBuffer sb = new StringBuffer();

		sb.append("<b>").append(assunto).append("</b><br/><br/>");
		sb.append("<b>Nome: </b>").append(contatoVH.getNome()).append("<br/><br/>");
		sb.append("<b>E-mail: </b>").append(contatoVH.getEmail()).append("<br/><br/>");
		sb.append("<b>Celular: </b>").append(contatoVH.getCelular()).append("<br/><br/>");
		sb.append("<b>Origem: </b>").append(contatoVH.getOrigem()).append("<br/><br/>");
		sb.append("<b>Destino: </b>").append(contatoVH.getDestino()).append("<br/><br/>");
		sb.append("<b>Data Ida: </b>").append(contatoVH.getDataIda()).append("<br/><br/>");
		sb.append("<b>Data Retorno: </b>").append(contatoVH.getDataRetorno()).append("<br/><br/>");
		sb.append("<b>Mensagem: </b>").append(contatoVH.getMensagem()).append("<br/><br/>");

		for (String emailTo : emailsLista) {

			Email e = new Email();

			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(emailTo);
			e.setReplyToEmail(contato.getEmail());
			e.setReplyToName(contato.getNome());
			e.setSubject(assunto);
			e.setMessage(sb.toString());

			emailNegocio.adicionarEmail(e);

		}
		
	}

	private String getPathCurriculo() {

		String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);

		String pathCurriculo = pathStatic + "/curriculos/";

		ArquivoUteis.verificarPastaExistente(pathCurriculo);

		return pathCurriculo;
	}

	@Transactional(propagation = Propagation.NESTED)
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public void vincularContatoAoGrupo(Contato contato, String grupo) {
		ContatoGrupo grupoContato = consultarGrupoContato(grupo);

		if (!contatoJaIncluidoGrupo(contato, grupoContato)) {
			ContatoXGrupo contatoXGrupo = new ContatoXGrupo();
			contatoXGrupo.setGrupo(grupoContato);
			contatoXGrupo.setContato(contato);

			inserir(contatoXGrupo);
		}
	}

	public Boolean contatoJaIncluidoGrupo(Contato contato, ContatoGrupo grupoContato) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("grupo", "t");
		dc.add(Restrictions.eq("t.id", grupoContato.getId()));
		dc.createAlias("contato", "c");
		dc.add(Restrictions.eq("c.id", contato.getId()));

		return hibernateUtil.possuiRegistros(dc);
	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public Contato cadastraContatoSite(String nome, String email, String telefone, String grupo) {

		if (Uteis.ehNuloOuVazio(nome)) {
			throw new NomeNuloException();
		}

		if (Uteis.ehNuloOuVazio(email)) {
			throw new EmailInvalidoException();
		}

		if (!Uteis.ehEmailValido(email)) {
			throw new EmailInvalidoException();
		}

		Contato contato = new Contato();

		contato.setEmail(email);

		contato = consultarContatoEmail(email);

		if (contato == null) {
			contato = new Contato();
		}

		contato.setEmail(email);
		contato.setNome(nome);

		if (!Uteis.ehNuloOuVazio(telefone)) {
			contato.setTelefone(telefone);
		}

		contato.setCadastradoPor("Web Site");
		contato.setPermissaoEmail(true);
		contato.setDataCriacao(new Date());

		if (contato.getId() == null) {
			contato = (Contato) inserir(contato);
		} else {
			atualizar(contato);
		}

		vincularContatoAoGrupo(contato, grupo);

		return contato;
	}

	public void enviarFormularioContatoEmail(Contato contato, String mensagem, String tipoFormulario) throws Exception {
		String emailsTo = null;
		String assuntoEmail = null;

		emailsTo = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);
		assuntoEmail = "[SITE] Contato - " + propNegocio.findValueById(PropEnum.NOME_EMPRESA);

		StringBuffer sb = new StringBuffer();
		sb.append("<b>").append(assuntoEmail).append("</b><br><br>");
		sb.append("<b>Nome: </b>").append(contato.getNome()).append("<br><br>");
		sb.append("<b>Empresa: </b>").append(contato.getEmpresa()).append("<br><br>");
		sb.append("<b>E-mail: </b>").append(contato.getEmail()).append("<br><br>");
		sb.append("<b>Telefone: </b>").append(contato.getTelefone()).append("<br><br>");
		sb.append("<b>Cidade: </b>").append(contato.getCidade()).append("<br><br>");
		sb.append("<b>Estado: </b>").append(contato.getEstado()).append("<br><br>");
		sb.append("<b>Mensagem: </b>").append(mensagem).append("<br><br>");

		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");

		for (String emailTo : emailsLista) {

			Email e = new Email();

			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(emailTo);
			e.setReplyToEmail(contato.getEmail());
			e.setReplyToName(contato.getNome());
			e.setSubject(assuntoEmail);
			e.setMessage(sb.toString());

			emailNegocio.adicionarEmail(e);

		}
	}

	public Contato cadastraContatoSite(Contato contato, String grupo) throws Exception {
		if (possuiContatoEmail(contato.getEmail())) {
			Contato contatoExistente = consultarContatoEmail(contato.getEmail());
			Long idContatoExistente = contatoExistente.getId();
			contatoExistente = contato;
			contatoExistente.setId(idContatoExistente);
			atualizar(contatoExistente);
			vincularContatoAoGrupo(contatoExistente, grupo);
			return contatoExistente;
		} else {
			contato = (Contato) inserir(contato);
			vincularContatoAoGrupo(contato, grupo);
			return contato;
		}
	}

	public boolean possuiContatoEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contato.class);
		dc.add(Restrictions.eq("email", email));

		return hibernateUtil.possuiRegistros(dc);
	}

	public void enviarFormularioContatoEmail(Contato contato, ContatoVH contatoVH) throws Exception {

		if (contatoVH.getEnviarEmail() == null || !contatoVH.getEnviarEmail()) {
			return;
		}

		String emailsTo = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);

		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");

		if (emailsLista == null) {
			logger.error("nenhum email na propriedade: " + PropEnum.EMAILS_CONTATO);
			return;
		}

		String assunto = "[SITE] Contato - " + propNegocio.findValueById(PropEnum.NOME_EMPRESA);

		StringBuffer sb = new StringBuffer();

		sb.append("<b>").append(assunto).append("</b><br/><br/>");
		sb.append("<b>Nome: </b>").append(contato.getNome()).append("<br/><br/>");
		sb.append("<b>E-mail: </b>").append(contato.getEmail()).append("<br/><br/>");
		sb.append("<b>Telefone: </b>").append(contato.getTelefone()).append("<br/><br/>");
//    sb.append("<b>Assunto: </b>").append(contato.getAssunto()).append("<br/><br/>");
		sb.append("<b>Mensagem: </b>").append(contatoVH.getMensagem()).append("<br/><br/>");

		for (String emailTo : emailsLista) {

			Email e = new Email();

			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(emailTo);
			e.setReplyToEmail(contato.getEmail());
			e.setToName(contato.getNome());
			e.setSubject(assunto);
			e.setMessage(sb.toString());			
			
			EmailSenderFactory emailSenderFactory = 
					SpringApplicationContext.getBean(EmailSenderFactory.class);
			emailSenderFactory.getInstance().send(e);
			
			

		}

	}

	public List<ContatoComentario> listarContatoComentarios(Contato contato) {

		DetachedCriteria dc = DetachedCriteria.forClass(ContatoComentario.class);

		dc.add(Restrictions.eq("contato.id", contato.getId()));

		dc.addOrder(Order.asc("data"));

		return (List<ContatoComentario>) hibernateUtil.list(dc);
	}

	public void validarContato(Contato contato) throws EmailInvalidoException, EhNuloOuVazioException {

		if (Uteis.ehNuloOuVazio(contato.getNome())) {
			throw new EhNuloOuVazioException("O nome não pode ser vazio");
		}

		if (Uteis.ehNuloOuVazio(contato.getEmail())) {
			throw new EhNuloOuVazioException("O email não pode ser vazio");
		}

		if (!Uteis.ehEmailValido(contato.getEmail())) {
			throw new EmailInvalidoException("O email inserido não é válido");
		}

		if (Uteis.ehNuloOuVazio(contato.getTelefone())) {
			throw new EhNuloOuVazioException("O telefone não pode ser vazio");
		}

		if (Uteis.ehNuloOuVazio(contato.getCidade())) {
			throw new EhNuloOuVazioException("A cidade não pode ser vazia");
		}

		if (Uteis.ehNuloOuVazio(contato.getMensagem())) {
			throw new EhNuloOuVazioException("A mensagem não pode ser vazia");
		}

	}

	@Transactional
	@CacheEvict(value = { "contato.grupos.com.qtd" }, allEntries = true)
	public void incluirAtualizarImportacao(Contato contato, ContatoGrupo contatoGrupo) {

		if (contato.getId() == null) {
			contato.setPermissaoEmail(true);
			contato = (Contato) inserir(contato);
		} else {
			atualizar(contato);
		}

		if (!contatoJaIncluidoGrupo(contato, contatoGrupo)) {
			ContatoXGrupo contatoXGrupo = new ContatoXGrupo();
			contatoXGrupo.setContato(contato);
			contatoXGrupo.setGrupo(contatoGrupo);

			inserir(contatoXGrupo);
		}

	}
}