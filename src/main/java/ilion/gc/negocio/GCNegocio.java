package ilion.gc.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.util.UteisGC;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
public class GCNegocio {

	static Logger logger = Logger.getLogger(GCNegocio.class);
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	UteisGC uteisGC;
	
	@Autowired
	ArquivoNegocio arquivoNegocio;
	
	private Map<String, String> caracteres;

	public GCNegocio() {
		super();
		caracteres = new HashMap<String, String>();
		caracteres.put("�", "&ccedil;");
		caracteres.put("�", "&aacute;");
		caracteres.put("�", "&eacute;");
		caracteres.put("�", "&iacute;");
		caracteres.put("�", "&oacute;");
		caracteres.put("�", "&uacute;");
		caracteres.put("�", "&atilde;");
		caracteres.put("�", "&otilde;");
		caracteres.put("�", "&acirc;");
		caracteres.put("�", "&ecirc;");
		caracteres.put("�", "&icirc;");
		caracteres.put("�", "&ocirc;");
		caracteres.put("�", "&ucirc;");
		caracteres.put("�", "&agrave;");
	}
	
	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}

	public Object inserir(Object object) {
		return hibernateUtil.save(object);
	}

	public void atualizar(Object object) {
		hibernateUtil.update(object);
	}

	public void excluir(Object object) {
		hibernateUtil.delete(object);
	}

	public CategoriaArtigo consultarIncluirCategoriaArtigo(CategoriaArtigo categoriaArtigo) {
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);

		String nome = categoriaArtigo.getNome();

		if(categoriaArtigo.getId() != null) {
			dc.add(Restrictions.eq("id", categoriaArtigo.getId()));
		} else {
			dc.add(Restrictions.eq("nome", nome));
		}

		categoriaArtigo = (CategoriaArtigo) hibernateUtil.uniqueResult(dc);

		if(categoriaArtigo == null && nome != null && nome.length() != 0) {
			categoriaArtigo = new CategoriaArtigo();
			categoriaArtigo.setNome(nome);

			categoriaArtigo = (CategoriaArtigo) hibernateUtil.save(categoriaArtigo);
		}

		return categoriaArtigo;
	}
	
	public CategoriaArtigo consultarCategoriaArtigo(CategoriaArtigo categoriaArtigo) {
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);

		String nome = categoriaArtigo.getNome();

		if(categoriaArtigo.getId() != null) {
			dc.add(Restrictions.eq("id", categoriaArtigo.getId()));
		} else {
			dc.add(Restrictions.eq("nome", nome));
		}

		categoriaArtigo = (CategoriaArtigo) hibernateUtil.uniqueResult(dc);

		return categoriaArtigo;
	}

	public ValueList subCategoriaBusca(String nomeCategoria, ValueListInfo valueListInfo) {
		DetachedCriteria dc = DetachedCriteria.forClass(SubCategoriaArtigo.class);
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.nome", nomeCategoria));

		return hibernateUtil.consultarValueList(dc, Order.asc("posicao"), valueListInfo);
	}

	public List subCategoriaLista(String nomeCategoria) {
		DetachedCriteria dc = DetachedCriteria.forClass(SubCategoriaArtigo.class);
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.nome", nomeCategoria));
		dc.addOrder(Order.asc("posicao"));

		return hibernateUtil.list(dc);
	}

	public Collection<String> converterCaracteres(String palavraInicial) {
		Collection<String> palavras = new ArrayList<String>();
		palavras.add(palavraInicial);

		String[] vetor1 = palavraInicial.split(" ");
		if(vetor1 != null) {
			for (String v : vetor1) {
				palavras.add(v);
			}
		}

		String palavraFinal = "";
		for (Iterator<String> iter = caracteres.keySet().iterator(); iter.hasNext();) {
			String k = (String) iter.next();
			if(palavraInicial.contains(k)) {
				palavraFinal = palavraInicial.replace(k,(String) caracteres.get(k));
			}
		}

		if(palavraFinal != null && palavraFinal.length() != 0) {
			palavras.add(palavraFinal);

			String[] vetor2 = palavraFinal.split(" ");
			if(vetor1 != null) {
				for (String v : vetor2) {
					palavras.add(v);
				}
			}
		}

		return palavras;
	}

	private List<Criterion> formarCriterions(String palavraChave, String coluna) {
		List<Criterion> criterions = new ArrayList<Criterion>();

		Collection<String> palavras = converterCaracteres(palavraChave);
		for (Iterator<String> iter = palavras.iterator(); iter.hasNext();) {
			String elemento = (String) iter.next();

			String like = "%"+elemento+"%";

			criterions.add(Restrictions.like(coluna, like));
		}

		return criterions;
	}
	
	private void addCriterions(DetachedCriteria dc, String palavraChave, String coluna) {
		if(palavraChave != null && palavraChave.length() != 0) {
			Collection<String> palavras = converterCaracteres(palavraChave);

			if(palavras.size() > 0) {

				List<Criterion> criterions = formarCriterions(palavraChave, coluna);

				Criterion cFinal = (Criterion) criterions.iterator().next();

				for (Iterator<Criterion> iter = criterions.iterator(); iter.hasNext();) {
					Criterion c = (Criterion) iter.next();
					cFinal = Restrictions.or(cFinal, c);
				}

				dc.add(cFinal);
			}
		}
	}

	public ValueList buscaGeral(VLHForm vlhForm, Order order, ValueListInfo valueListInfo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class);
		
		if(vlhForm.getPalavraChave() != null) {
			Integer posicao = Uteis.converterInteger(vlhForm.getPalavraChave());
			if(posicao != null) {
				Criterion cPosicao = Restrictions.eq("posicao", posicao);
				
				detachedCriteria.add(cPosicao);
			} else {
				Collection<Criterion> criterions = formarCriterions(vlhForm.getPalavraChave(), "titulo");
				
				Criterion cFinal = (Criterion) criterions.iterator().next();
				
				for (Iterator<Criterion> iter = criterions.iterator(); iter.hasNext();) {
					Criterion c = (Criterion) iter.next();
					cFinal = Restrictions.or(cFinal, c);
				}
				
				DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);
				addCriterions(dc, vlhForm.getPalavraChave(), "texto");
				dc.setProjection(Projections.property("artigo.id"));
				
				Criterion cPalavraChave = Restrictions.or(cFinal, Subqueries.propertyIn("id", dc));
				
				detachedCriteria.add(cPalavraChave);
			}
		}

		return hibernateUtil.consultarValueList(detachedCriteria, order, valueListInfo);
	}
	
	public ValueList busca(VLHForm vlhForm, String orderBy, ValueListInfo valueListInfo) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class)
		.createAlias("categoriaArtigo", "c")
		.add(Restrictions.eq("c.id", vlhForm.getIdCategoria()));
		
		if(vlhForm.getIdSubCategoria() != null && vlhForm.getIdSubCategoria() != 0) {
			detachedCriteria.createAlias("subCategoria", "s");
			detachedCriteria.add(Restrictions.eq("s.id", vlhForm.getIdSubCategoria()));
		}
		
		if(vlhForm.getPalavraChave() != null && vlhForm.getPalavraChave().length() != 0) {
			Integer posicao = Uteis.converterInteger(vlhForm.getPalavraChave());
			if(posicao != null) {
				Criterion cPosicao = Restrictions.eq("posicao", posicao);
				
				detachedCriteria.add(cPosicao);
			} else {
				Collection<Criterion> criterions = formarCriterions(vlhForm.getPalavraChave(), "titulo");
				
				Criterion cFinal = (Criterion) criterions.iterator().next();
				
				for (Iterator<Criterion> iter = criterions.iterator(); iter.hasNext();) {
					Criterion c = (Criterion) iter.next();
					cFinal = Restrictions.or(cFinal, c);
				}
				
				String like = "%>"+vlhForm.getPalavraChave()+"<%";
				cFinal = Restrictions.or(cFinal, Restrictions.like("secoes", like));
				
				DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);
				addCriterions(dc, vlhForm.getPalavraChave(), "texto");
				dc.setProjection(Projections.property("artigo.id"));
				
				Criterion cPalavraChave = Restrictions.or(cFinal, Subqueries.propertyIn("id", dc));
				
				detachedCriteria.add(cPalavraChave);
			}
		}
		
		Order order = hibernateUtil.obterOrderBy(orderBy);

		return hibernateUtil.consultarValueList(detachedCriteria, order, valueListInfo);
	}

	public Boolean possuiArtigos(SubCategoriaArtigo subCategoriaArtigo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		dc.createAlias("subCategoria", "s");
		dc.add(Restrictions.eq("s.id", subCategoriaArtigo.getId()));
		
		return hibernateUtil.possuiRegistros(dc);
	}
	
	@Transactional
	public void excluirSubCategoriaArtigo(SubCategoriaArtigo subCategoriaArtigo) {
		arquivoNegocio.excluirArquivos(SubCategoriaArtigo.class.getSimpleName(), subCategoriaArtigo.getId());
		
		hibernateUtil.delete(subCategoriaArtigo);
	}
	
	public Integer qtdArtigos(SubCategoriaArtigo subCategoriaArtigo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		dc.createAlias("subCategoria", "s");
		dc.add(Restrictions.eq("s.id", subCategoriaArtigo.getId()));
		
		return hibernateUtil.consultarQtd(dc).intValue();
	}
	
	public List listarArtigos(String nomeCategoria) {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.nome", nomeCategoria));
		dc.addOrder(Order.desc("dataPublicacao"));
		
		return hibernateUtil.list(dc);
	}

	public List artigosEmAprovacao() {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		dc.add(Restrictions.eq("status", "Em Aprovação"));
		dc.addOrder(Order.asc("dataPublicacao"));
		
		return hibernateUtil.list(dc);
	}
	
	public List comentariosEmAprovacao() {
		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		dc.add(Restrictions.eq("status", "EmAprovacao"));
		
		return hibernateUtil.list(dc);
	}

	public ValueList buscaArtigosExcetoAprovacao(VLHForm vlhForm, Order order, ValueListInfo valueListInfo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class)
		.createAlias("categoriaArtigo", "c")
		.add(Restrictions.eq("c.nome", vlhForm.getNomeCategoria()));

		if(vlhForm.getIdSubCategoria() != null && vlhForm.getIdSubCategoria() != 0) {
			detachedCriteria.createAlias("subCategoria", "s");
			detachedCriteria.add(Restrictions.eq("s.id", vlhForm.getIdSubCategoria()));
		}
		
		if(vlhForm.getPalavraChave() != null) {
			Integer posicao = Uteis.converterInteger(vlhForm.getPalavraChave());
			if(posicao != null) {
				Criterion cPosicao = Restrictions.eq("posicao", posicao);
				
				detachedCriteria.add(cPosicao);
			} else {
				Collection<Criterion> criterions = formarCriterions(vlhForm.getPalavraChave(), "titulo");
				
				Criterion cFinal = (Criterion) criterions.iterator().next();
				
				for (Iterator<Criterion> iter = criterions.iterator(); iter.hasNext();) {
					Criterion c = (Criterion) iter.next();
					cFinal = Restrictions.or(cFinal, c);
				}
				
				DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);
				addCriterions(dc, vlhForm.getPalavraChave(), "texto");
				dc.setProjection(Projections.property("artigo.id"));
				
				Criterion cPalavraChave = Restrictions.or(cFinal, Subqueries.propertyIn("id", dc));
				
				detachedCriteria.add(cPalavraChave);
			}
		}

		return hibernateUtil.consultarValueList(detachedCriteria, order, valueListInfo);
	}

	public String identificarStatus(Artigo artigo) {
		if("Em Aprovação".equals(artigo.getStatus())) {
			return "Em Aprovação";
		}
		
		if( artigo.getDataPublicacao() == null && 
				artigo.getDataExpiracao() == null ) {
			return "Sem datas";
		}
		
		Date agora = new Date();
		
		if(artigo.getDataPublicacao().after(agora)) {
			return "Aguardando";
		}
		
		if(artigo.getDataExpiracao().after(agora)) {
			return artigo.getStatus();
		}
		
		if(artigo.getDataExpiracao().before(agora)) {
			return "Fora de data";
		}
		
		return "---";
	}
	
}