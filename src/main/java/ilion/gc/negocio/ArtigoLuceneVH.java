package ilion.gc.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.gc.taglibs.UrlAmigavel;
import ilion.gc.util.UteisGC;
import ilion.gc.util.UteisGC.CONTEUDOINFO_NOS;
import ilion.util.Html2Text;
import ilion.util.StringUtil;
import ilion.util.Uteis;

public class ArtigoLuceneVH implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ArtigoLuceneVH.class);
	
	private String id;
	private String urlRelativa;
	private String titulo;
	private String resumo;
	private String texto;
	private String conteudoInfo;
	private Date dataPublicacao;
	private String dataPublicacaoString;
	private String conteudo;
	
	public ArtigoLuceneVH() {
		super();
	}
	
	public ArtigoLuceneVH(Document hitDoc) {
		this.id = hitDoc.get("id");
		this.urlRelativa = hitDoc.get("urlRelativa");
		this.titulo = hitDoc.get("titulo");
		this.resumo = hitDoc.get("resumo");
		this.texto = hitDoc.get("texto");
		this.conteudoInfo = hitDoc.get("conteudoInfo");
		this.dataPublicacaoString = hitDoc.get("dataPublicacao");
		this.dataPublicacao = Uteis.converterDataHora(dataPublicacaoString, "dd/MM/yyyy HH:mm");
		this.conteudo = hitDoc.get("conteudo");
	}
	
	public ArtigoLuceneVH(Artigo artigo) {
		
		String resumo = concatenarArtigoConteudo(artigo, CONTEUDOINFO_NOS.RESUMO);
		String texto = concatenarArtigoConteudo(artigo, CONTEUDOINFO_NOS.TEXTO);
		
		this.setId(artigo.getId().toString());
		this.setTitulo(artigo.getTitulo());
		this.setResumo(resumo);
		this.setTexto(texto);
		this.setConteudoInfo(artigo.getConteudoInfo());
		this.setDataPublicacao(artigo.getDataPublicacao());
		this.setDataPublicacaoString(Uteis.formatarDataHora(artigo.getDataPublicacao(), "dd/MM/yyyy HH:mm"));
		
		String urlRelativa = formarUrlRelativa(artigo);
		
		this.setUrlRelativa(urlRelativa);
	}
	
	private String concatenarArtigoConteudo(Artigo artigo, UteisGC.CONTEUDOINFO_NOS conteudoInfoNo) {
		String possuiConteudo = (String) StringUtil.buscarValor(artigo.getConteudoInfo(), conteudoInfoNo, String.class);
		
		StringBuilder textoRetorno = new StringBuilder();
		
		if("true".equals(possuiConteudo)) {
			
			GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);
			List<Map<String, Object>> resumoConteudos = gcSiteNegocio.listarArtigoConteudoSite(artigo, conteudoInfoNo.toString().toLowerCase(), "texto;");
			
			for (Iterator<Map<String, Object>> iterator = resumoConteudos.iterator(); iterator.hasNext();) {
				Map<String, Object> cMap = iterator.next();
				
				String texto = (String) cMap.get("texto");
				
				textoRetorno.append(texto);
				textoRetorno.append(" ");
			}
		}
		
		return textoRetorno.toString();
	}

	private String formarUrlRelativa(Artigo artigo) {
		String categoriaUrl = artigo.getCategoriaArtigo().getNome();
		String subCategoriaUrl = null;
		if(artigo.getSubCategoria() != null) {
			subCategoriaUrl = artigo.getSubCategoria().getEnderecoUrl();
		}
		String artigoUrl = artigo.getEnderecoUrl();
		
		StringBuilder link = new StringBuilder();
		try {
			
			UrlAmigavel urlAmigavel = new UrlAmigavel();
			
			urlAmigavel.setCategoria(categoriaUrl);
			urlAmigavel.setUrlSubCategoria(subCategoriaUrl);
			urlAmigavel.setUrlArtigo(artigoUrl);
			link.append(urlAmigavel.toString());
			
		} catch (Exception e) {
			String m = "erro ao formarUrlRelativa";
			logger.error(m, e);
		}
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		String urlRelativa = link.substring(propNegocio.findValueById(PropEnum.URL).length()+1);
		
		return urlRelativa;
	}
	
	public Document toDocument() {
		
		Document doc = new Document();
		
		doc.add(new Field("id", this.getId(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("titulo", this.getTitulo(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("dataPublicacao", this.getDataPublicacaoString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("conteudoInfo", this.getConteudoInfo(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("urlRelativa", this.getUrlRelativa(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		doc.add(new Field("conteudo", this.getConteudo(), Field.Store.YES, Field.Index.ANALYZED));
		
		return doc;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConteudoInfo() {
		return conteudoInfo;
	}
	public void setConteudoInfo(String conteudoInfo) {
		this.conteudoInfo = conteudoInfo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		titulo = Html2Text.parseReturn(titulo);
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		resumo = Html2Text.parseReturn(resumo);
		this.resumo = resumo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		texto = Html2Text.parseReturn(texto);
		this.texto = texto;
	}

	public String getConteudo() {

		if( conteudo == null ) {
			StringBuilder sb = new StringBuilder();
			
			if(dataPublicacaoString != null) {
				sb.append(dataPublicacaoString);
				sb.append(" ");
			}
			if(titulo != null) {
				sb.append(titulo);
				sb.append(" ");
			}
			if(resumo != null && resumo.length() != 0) {
				sb.append(resumo);
				sb.append(" ");
			}
			if(texto != null && texto.length() != 0) {
				sb.append(texto);
				sb.append(" ");
			}
			
			conteudo = sb.toString();
		}

		return conteudo;
	}

	public String getUrlRelativa() {
		return urlRelativa;
	}

	public void setUrlRelativa(String urlRelativa) {
		this.urlRelativa = urlRelativa;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getDataPublicacaoString() {
		return dataPublicacaoString;
	}

	public void setDataPublicacaoString(String dataPublicacaoString) {
		this.dataPublicacaoString = dataPublicacaoString;
	}
	
	public String getPreview() {
		//Integer qtd = 300;
		String preview = null;
		try {
			int beginIndex = getConteudo().indexOf(getTitulo())+getTitulo().length();
			int endIndex = getConteudo().length();
			preview = getConteudo().substring(beginIndex, endIndex);
		} catch (Exception e) {
			logger.error("Erro ao obter texto preview - busca lucene!", e);
		}
		
		if(!Uteis.ehNuloOuVazio(preview)){
			preview += " ...";			
		}
		
		return preview;
	}
}