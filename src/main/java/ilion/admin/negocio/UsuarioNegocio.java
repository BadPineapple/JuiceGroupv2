package ilion.admin.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.SpringApplicationContext;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailSenderFactory;
import ilion.terrafos.auth.negocio.TokenDTO;
import ilion.terrafos.auth.negocio.UsuarioProfileVH;
import ilion.util.JWTUtil;
import ilion.util.StringUtil;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.exceptions.EmailInvalidoException;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.json.GSONUteis;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
public class UsuarioNegocio {
	
	private Logger logger = Logger.getLogger(UsuarioNegocio.class);
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Transactional
	public Boolean inserirDadosIniciais() {
		
		if( hibernateUtil.possuiRegistros(Perfil.class) ) {
			return false;
		}
		
		try {
			
			Perfil perfil = new Perfil();
			perfil.setNome("Admin");
			perfil.setAcessoAoSistema(true);
			
			perfil = (Perfil) inserir(perfil);
			
			List<String> permissoes = new ArrayList<String>();
			permissoes.add("usuario.sp");
			permissoes.add("usuario-form.sp");
			permissoes.add("usuario-excluir.sp");
			permissoes.add("perfil.sp");
			permissoes.add("perfil-form.sp");
			permissoes.add("perfil-excluir.sp");
			
			String permissoesJson = GSONUteis.getInstance().toJson(permissoes);
			perfil.setPermissoesJson(permissoesJson);
			
			perfil = (Perfil) inserir(perfil);
			
			Usuario usuario = new Usuario();
			usuario.setNome("Ilion Solucoes");
			usuario.setPerfil(perfil);
			usuario.setLogin("ilion");
			usuario.setSenha("aWxpb24xMjM=");
			usuario.setStatus("Ativo");
			usuario.setEmail("suporte@ilion.com.br");
			
			inserir(usuario);
			
			return true;
		} catch (Exception e) {
			logger.error("dados iniciais", e);
			return false;
		}
	}
	
	public Usuario consultarPorId(Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(Usuario.class);
		dc.setFetchMode("fazendas", FetchMode.JOIN);
		dc.add(Restrictions.eq("id", id));
		return (Usuario) hibernateUtil.uniqueResult(dc);
	}

	public Usuario consultarLogin(String login) {
		DetachedCriteria dc = DetachedCriteria.forClass(Usuario.class);
		dc.createAlias("perfil", "p");
		dc.add(Restrictions.eq("p.acessoAoSistema", true));
		dc.add(Restrictions.eq("login", login));
		dc.setFetchMode("fazendas", FetchMode.JOIN);

		return (Usuario) hibernateUtil.uniqueResult(dc);
	}
	
	public Usuario consultarEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Usuario.class);
		dc.createAlias("perfil", "p");
		dc.add(Restrictions.eq("p.acessoAoSistema", true));
		dc.add(Restrictions.eq("email", email));
		
		return (Usuario) hibernateUtil.uniqueResult(dc);
	}

	public Boolean existeLogin(Usuario usuario) {
		DetachedCriteria dc = DetachedCriteria.forClass(Usuario.class);
		dc.add(Restrictions.eq("login", usuario.getLogin()));

		if(usuario.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", usuario.getId())));
		}

		return hibernateUtil.possuiRegistros(dc);
	}
	
	public Boolean existeEmail(Usuario usuario) {
		DetachedCriteria dc = DetachedCriteria.forClass(Usuario.class);
		dc.add(Restrictions.eq("email", usuario.getEmail()));

		if(usuario.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", usuario.getId())));
		}
		
		return hibernateUtil.possuiRegistros(dc);
	}

	public void validarUsuario(Usuario usuario, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome não pode ser vazio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "email", "", "E-mail não pode ser vazio.");
		
		if( ! Uteis.ehNuloOuVazio(usuario.getEmail()) ) {
			if( ! Uteis.ehEmailValido(usuario.getEmail()) ) {
				bindingResult.rejectValue("email", "", "E-mail deve ser válido.");
			} else if( existeEmail(usuario) ) {
				bindingResult.rejectValue("email", "", "E-mail já existente, por favor digite outro.");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "status", "", "Status não selecionado.");

		if(usuario.getPerfil().getId() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "login", "", "Login não pode ser vazio.");
		}

		if( ! Uteis.ehNuloOuVazio(usuario.getLogin()) ) {
			if( existeLogin(usuario) ) {
				bindingResult.rejectValue("login", "", "Login já existente, por favor digite outro.");
			}
		}
		
		if( Uteis.ehNuloOuVazio(usuario.getSenhaAux()) ) {
			bindingResult.rejectValue("senhaAux", "", "Senha deve ser preenchida.");
		}
	}

	public void validarUsuarioSessao(Usuario usuario, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome não pode ser vazio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "email", "", "E-mail não pode ser vazio.");
		
		if( ! Uteis.ehNuloOuVazio(usuario.getEmail()) ) {
			if( ! Uteis.ehEmailValido(usuario.getEmail()) ) {
				bindingResult.rejectValue("email", "", "E-mail deve ser válido.");
			} else if( existeEmail(usuario) ) {
				bindingResult.rejectValue("email", "", "E-mail já existente, por favor digite outro.");
			}
		}
		
		if(usuario.getPerfil().getId() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "login", "", "Login não pode ser vazio.");
		}

		if( ! Uteis.ehNuloOuVazio(usuario.getLogin()) ) {
			if( existeLogin(usuario) ) {
				bindingResult.rejectValue("login", "", "Login já existente, por favor digite outro.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "senha", "", "Senha não pode ser vazia.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "confirmar", "", "Confirme a senha.");
		
		if(usuario.getSenha() != null) {
			if( ! usuario.getSenha().equals(usuario.getConfirmar())) {
				bindingResult.rejectValue("confirmar", "", "Senha e confirmar senha não conferem.");
			}
		}
	}

	public ValueList busca(VLHForm vlhForm, ValueListInfo valueListInfo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Usuario.class);

		return hibernateUtil.consultarValueList(dc, Order.asc("nome"), valueListInfo);
	}

	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}

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

	@SuppressWarnings("unchecked")
	public List<String> listarPerfilPermissoes(Perfil perfil) {
		
		List<String> permissoes = Collections.EMPTY_LIST;
		
		if( ! Uteis.ehNuloOuVazio(perfil.getPermissoesJson()) ) {
			permissoes = GSONUteis.getInstance().fromJson(perfil.getPermissoesJson(), List.class);
		}
		
		return permissoes;
	}

	public String novaSenha() {
		String s = "usuario";

		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			s += r.nextInt(10);
		}

		return s;
	}
	
	public void enviarSenhaEmail(String email) throws Exception {
		
		if( Uteis.ehNuloOuVazio(email) ) {
			throw new EmailInvalidoException();
    	}
    	
    	if( ! Uteis.ehEmailValido(email) ) {
    		throw new EmailInvalidoException();
    	}
    	
    	logger.info("tentando esqueci minha senha: ["+email+"]");
    	
    	Usuario usuario = consultarLogin(email);
    	
    	if( usuario == null) {
    		usuario = consultarEmail(email);
    	}
    	
    	if( usuario == null) {
    		
    		throw new ValidacaoException("Usuário não encontrado.");
    		
    	} else if( ! "Ativo".equals(usuario.getStatus())) {
    		
    		throw new ValidacaoException("Usuário inativo.");
    		
    	}
    	
    	EnviarSenhaEmailThread.novo(usuario);
		
	}
	
	public void enviarSenhaEmail(Usuario usuario) throws Exception {
		String urlProp = propNegocio.findValueById(PropEnum.URL);
		String nomeEmpresaProp = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		
		String url = urlProp+"/ilionnet/template-esqueci-minha-senha?id="+usuario.getId();
		
		String assunto = "Envio de senha por e-mail - "+nomeEmpresaProp;
		
		String html = Uteis.getHtml(url);
		
		String senha = usuario.getSenha();
		senha = StringUtil.decodePassword(senha);
		
		html = html.replaceAll("#senha#", senha);
		
		Email e = new Email();
		
		e.setToEmail(usuario.getEmail());
		e.setToName(usuario.getNome());
		e.setSubject(assunto);
		e.setMessage(html);
		
		EmailSenderFactory emailSenderFactory = 
				SpringApplicationContext.getBean(EmailSenderFactory.class);
		emailSenderFactory.getInstance().send(e);
	}
	
	@Transactional
	public void incluirAtualizar(Usuario usuario) throws Exception {
		
		if(usuario.getPerfil().getId() == null) {
			usuario.setPerfil(null);
		}
		
		String senha = StringUtil.encodePassword(usuario.getSenhaAux());
		usuario.setSenha(senha);
		
		if(usuario.getId() == null) {
			
			usuario = (Usuario) hibernateUtil.save(usuario);
			
			EnviarSenhaEmailThread.novo(usuario);
			
		} else {
			hibernateUtil.update(usuario);
		}
		
	}
	
	public Usuario efetuarLogin(String login, String senha) {
		
		logger.info("tentando login: ["+login+"]");
    	
    	Usuario usuario = consultarLogin(login);
    	
    	if( usuario == null) {
    		
    		throw new ValidacaoException("Usuário não encontrado.");
    		
    	} else if( ! "Ativo".equals(usuario.getStatus())) {
    		
    		throw new ValidacaoException("Usuário inativo.");
    		
    	} else if( ! StringUtil.encode(senha).equals(usuario.getSenha())) {
    		
    		throw new ValidacaoException("Usuário/Senha incorreta.");
    		
    	}
    	
    	usuario.setPermissoes(listarPerfilPermissoes(usuario.getPerfil()));
    	
    	logger.info("login: ["+login+"] OK");
		
    	return usuario;
	}

	public TokenDTO loginToken(UsuarioProfileVH usuarioProfileVH, String origem) {
		
		String json = GSONUteis.getInstance().toJson(usuarioProfileVH.getId());
		
		String token = jwtUtil.create(json);
		
		TokenDTO tokenDTO = new TokenDTO(token);
		
		String email = usuarioProfileVH.getEmail();
		logger.info("logged OK: [" + email + "], origem: "+origem);
		
		return tokenDTO;
	}
}