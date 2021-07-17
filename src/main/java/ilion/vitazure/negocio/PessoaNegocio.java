package ilion.vitazure.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.util.StatusEnum;
import ilion.util.StringUtil;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.Pessoa;
import net.mlw.vlh.ValueList;

@Service
@SuppressWarnings("unchecked")
public class PessoaNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public final static String ATRIBUTO_SESSAO = "pessoaSessao";
	
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

		Pessoa pessoaVO = consultarPorEmail(pessoa.getEmail());
		
		if (pessoaVO == null) {
			throw new ValidacaoException("Usuario não Encontrado.");
		}
		
		if(!StringUtil.decodePassword(pessoaVO.getSenha()).equals(pessoa.getSenha())) {
			throw new ValidacaoException("Senha não confere.");
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
		
		ValueList notificacaos = hibernateUtil.consultarValueList(dc, org.hibernate.criterion.Order.desc("id"), valueListInfo);

		return notificacaos;

	}
	
}
