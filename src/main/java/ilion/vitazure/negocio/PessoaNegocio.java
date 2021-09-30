package ilion.vitazure.negocio;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.EnviarSenhaEmailThread;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.Usuario;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailNegocio;
import ilion.email.negocio.EmailSenderFactory;
import ilion.util.StatusEnum;
import ilion.util.StringUtil;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
import ilion.util.exceptions.EmailInvalidoException;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.Pessoa;
import net.mlw.vlh.ValueList;

@Service
@SuppressWarnings("unchecked")
public class PessoaNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PropNegocio propNegocio;
	
	public final static String ATRIBUTO_SESSAO = "pessoaSessao";
	
	public final static String AGENDA_SESSAO = "agendaPessoa";
	
	public final static String PROFISSIONAL_COMPLETO = "profissonalSessaoCompleto";
	
	@Autowired
	private EmailNegocio emailNegocio;
	
	public Pessoa consultarPorId(Long id) {
		return (Pessoa) hibernateUtil.findById(Pessoa.class, id);
	}
	
	public Pessoa consultarPorEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Pessoa.class);
		dc.add(Restrictions.eq("email", email));
		return (Pessoa) hibernateUtil.consultarUniqueResult(dc);
	}

	@Transactional
	public Pessoa incluirAtualizar(Pessoa pessoa) throws Exception{
		validarUsuario(pessoa);
		pessoa = criptografarSenha(pessoa);
		if (pessoa.getId() == null || pessoa.getId() == 0) {
			pessoa = (Pessoa) hibernateUtil.save(pessoa);
		} else {
			hibernateUtil.update(pessoa);
		}
		return pessoa;
	}
	
	@Transactional
	public void excluir(Long idPessoa) {
		Pessoa pessoa = (Pessoa) hibernateUtil.findById(Pessoa.class, idPessoa);
		hibernateUtil.delete(pessoa);
	}
	
	
	public Pessoa login(Pessoa pessoa) throws Exception{
		
		if (Uteis.ehNuloOuVazio(pessoa.getEmail())) {
			throw new ValidacaoException("Usuario deve ser preenchida.");
		}
		
		if (Uteis.ehNuloOuVazio(pessoa.getSenha())) {
			throw new ValidacaoException("Senha deve ser preenchida.");
		}

		Pessoa pessoaVO = consultarPorEmail(pessoa.getEmail().toLowerCase(Locale.ROOT));
		
		if (pessoaVO == null) {
			throw new ValidacaoException("Usuario não Encontrado.");
		}
		
		if(!StringUtil.decodePassword(pessoaVO.getSenha()).equals(pessoa.getSenha())) {
			throw new ValidacaoException("Senha não confere.");
		}
		
		if (pessoaVO.getConfirmado() == null || !pessoaVO.getConfirmado()) {
			throw new ValidacaoException("Usuário ainda não ativado. Por favor, confira o link de ativação enviado no e-mail cadastrado.");
		}
		
		return pessoaVO;
	}
	
	
	private Pessoa criptografarSenha(Pessoa pessoa) {
		 
		String senha = "";
		Pessoa user = new Pessoa();
		
		if (pessoa.getId() == null || pessoa.getId() == 0) {
			senha = StringUtil.encodePassword(pessoa.getSenha());
		}else {
			user = consultarPorId(pessoa.getId());
			if (!StringUtil.decodePassword(user.getSenha()).equals(StringUtil.decodePassword(pessoa.getSenha()))) {
				senha = StringUtil.encodePassword(pessoa.getSenha());
			}
		}
		if (senha != null && !senha.equals("")) {
			pessoa.setSenha(senha);
		}
		
		user = null;
		return  pessoa;
	}
	
	public List<Pessoa> consultarProfissionais(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Pessoa.class);
		dc.add(Restrictions.eq("psicologo", true));
		return (List<Pessoa>) hibernateUtil.list(dc);
	}
	
	public ValueList buscar(VLHForm vlhForm, ValueListInfo valueListInfo , Usuario usuarioSessao , String tipoPessoa) {

		DetachedCriteria dc = DetachedCriteria.forClass(Pessoa.class);
		dc.add(Restrictions.eq(tipoPessoa, true));
		if (!Uteis.ehNuloOuVazio(vlhForm.getPalavraChave())) {
			Disjunction disjunction = Restrictions.disjunction();
			List<String> condicoes = PalavrasChaveCondicoes.nova().comPalavrasChave(vlhForm.getPalavraChave()).gerar();
			for (String condicao : condicoes) {
				disjunction.add( Restrictions.ilike("nome", condicao));
				disjunction.add( Restrictions.ilike("email", condicao));
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
		
		if (!usuarioSessao.getAdmin()) {
			dc.add(Restrictions.eq("empresaImportacao", usuarioSessao.getEmpresa()));
		}
		
		ValueList notificacaos = hibernateUtil.consultarValueList(dc, org.hibernate.criterion.Order.desc("id"), valueListInfo);

		return notificacaos;

	}
	
	public void validarUsuario(Pessoa pessoa) throws Exception{

		if( ! Uteis.ehNuloOuVazio(pessoa.getEmail()) ) {
			if( existeEmailJaCadastrado(pessoa) ) {
				throw new ValidacaoException("Email já Cadastrado.");
			}
		}
		
	}
	
	public Boolean existeEmailJaCadastrado(Pessoa pessoa) {
		DetachedCriteria dc = DetachedCriteria.forClass(Pessoa.class);
		dc.add(Restrictions.eq("email", pessoa.getEmail()));

		if(pessoa.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", pessoa.getId())));
		}

		return hibernateUtil.possuiRegistros(dc);
	}
	
public void enviarSenhaEmail(String email) throws Exception {
		
		if( Uteis.ehNuloOuVazio(email) ) {
			throw new EmailInvalidoException();
    	}
    	
    	if( ! Uteis.ehEmailValido(email) ) {
    		throw new EmailInvalidoException();
    	}
    	
    	Pessoa pessoaVO = consultarPorEmail(email);
    	
    	if( pessoaVO == null) {
    		throw new ValidacaoException("Usuário não encontrado.");
    	} 
    	
    	EnviarEmailTheread.novo(pessoaVO);
		
	}
	
	public void esqueciMinhaSenhaEmail(Pessoa pessoaVO) throws Exception {
		
		String urlProp = propNegocio.findValueById(PropEnum.URL);
		String nomeEmpresaProp = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		
		String url = urlProp+"/ilionnet/templateEsqueciSenha?id="+pessoaVO.getId();
		
		String assunto = "Envio de senha por e-mail - "+nomeEmpresaProp;
		
		String html = Uteis.getHtml(url);
		
		String senha = pessoaVO.getSenha();
		senha = StringUtil.decodePassword(senha);
		
		html = html.replaceAll("#senha#", senha);
		
		Email e = new Email();
		
		e.setToEmail(pessoaVO.getEmail());
		e.setToName(pessoaVO.getNome());
		e.setSubject(assunto);
		e.setMessage(html);
		
		EmailSenderFactory emailSenderFactory = 
				SpringApplicationContext.getBean(EmailSenderFactory.class);
		emailSenderFactory.getInstance().send(e);
	}
	
	
	public void enviarEmailAtivacao(Pessoa pessoa) throws Exception {
	    	EnvioEmailConfirmacao.novo(pessoa);
	}
	
public void emailAtivacao(Pessoa pessoaVO) throws Exception {
		
		String urlProp = propNegocio.findValueById(PropEnum.URL);
		String nomeEmpresaProp = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		String url = urlProp+"/ilionnet/templateConfirmacao?id="+pessoaVO.getId();
		String assunto = "Ativação - "+nomeEmpresaProp;
		String html = Uteis.getHtml(url);
		html = new String(html.getBytes(StandardCharsets.ISO_8859_1));
		Email e = new Email();
		e.setToEmail(pessoaVO.getEmail());
		e.setToName(pessoaVO.getNome());
		e.setSubject(assunto);
		e.setMessage(html);
		emailNegocio.adicionarEmail(e);
	}	

}
