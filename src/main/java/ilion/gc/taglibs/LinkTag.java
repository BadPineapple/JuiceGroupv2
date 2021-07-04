package ilion.gc.taglibs;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoMenuEnum;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.util.Uteis;

public class LinkTag extends TagSupport {

	static Logger logger = Logger.getLogger(LinkTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uri;
	private String categoria;
	private Object obj;


	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		try {
			definirLink(out);
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
	
	private void definirLink(Appendable out) throws IOException {
		
		if( ! Uteis.ehNuloOuVazio(uri) ) {
			
			appendLinkURI(out);
			
			return;
		}
		
		if(obj == null) {
			
			appendLinkCategoria(out);
			
			return;
		}
		
		if(obj instanceof Map) {
			
			appendLinkMap((Map) obj, out);
			
		} else if(obj instanceof Artigo) {
			
			appendLinkArtigo((Artigo) obj, out);
			
		} else if(obj instanceof SubCategoriaArtigo) {
			
			appendLinkSubCategoria( (SubCategoriaArtigo) obj, out );
			
		}
		
	}
	
	private void appendLinkCategoria(Appendable out) throws IOException {
		
		if( Uteis.ehNuloOuVazio(categoria) ) {
			return;
		}
		
		UrlAmigavel urlAmigavel = 
				SpringApplicationContext
				.getBean(UrlAmigavelFlyweight.class)
				.get(null, categoria, null, null);
		
		out.append(urlAmigavel.getUrlFinal());
		
	}
	
	private void appendLinkURI(Appendable out) throws IOException {
		
		UrlAmigavel urlAmigavel = 
				SpringApplicationContext
				.getBean(UrlAmigavelFlyweight.class)
				.get(uri, null, null, null);
		
		out.append(urlAmigavel.getUrlFinal());
		
	}
	
	private void appendLinkMap(Map map, Appendable out) throws IOException {
		
		String _enderecoUrlSubCategoria = null;
		String _enderecoUrlArtigo = null;
		
		if(map.containsKey("subCategoria")) {
			SubCategoriaArtigo subCategoria = (SubCategoriaArtigo) map.get("subCategoria");
			_enderecoUrlSubCategoria = subCategoria.getEnderecoUrl();
		}
		
		_enderecoUrlArtigo = (String) map.get("enderecoUrl");
		
		UrlAmigavel urlAmigavel = 
				SpringApplicationContext
				.getBean(UrlAmigavelFlyweight.class)
				.get(null, categoria, _enderecoUrlSubCategoria, _enderecoUrlArtigo);
		
		out.append(urlAmigavel.getUrlFinal());
	}
	
	private void appendLinkArtigo(Artigo artigo, Appendable out) throws IOException {
		
		ArtigoMenuEnum artigoMenuEnum = artigo.getArtigoMenuEnum();
		
		if( ArtigoMenuEnum.LINK.equals(artigoMenuEnum) ) {
			
			appendLinkArtigoLink(artigo, out);
			
		} else {
			
			appendLinkArtigoConteudo(artigo, out);
			
		}
		
	}
	
	private void appendLinkArtigoLink(Artigo artigo, Appendable out) throws IOException {
		
		out.append(artigo.getLink());
		
	}
	
	private void appendLinkArtigoConteudo(Artigo artigo, Appendable out) throws IOException {
		String _categoria = null;
		String _enderecoUrlSubCategoria = null;
		String _enderecoUrlArtigo = null;
		
		_categoria = artigo.getCategoriaArtigo().getNome();

		if(artigo.getSubCategoria() != null) {
			_enderecoUrlSubCategoria = artigo.getSubCategoria().getEnderecoUrl();
		}

		_enderecoUrlArtigo = artigo.getEnderecoUrl();
		
		UrlAmigavel urlAmigavel = 
				SpringApplicationContext
				.getBean(UrlAmigavelFlyweight.class)
				.get(null, _categoria, _enderecoUrlSubCategoria, _enderecoUrlArtigo);
		
		out.append(urlAmigavel.getUrlFinal());
	}
	
	private void appendLinkSubCategoria(SubCategoriaArtigo s, Appendable out) throws IOException {
		String _categoria = null;
		String _enderecoUrlSubCategoria = null;
		
		_categoria = s.getCategoriaArtigo().getNome();
		
		_enderecoUrlSubCategoria = s.getEnderecoUrl();
		
		UrlAmigavel urlAmigavel = 
				SpringApplicationContext
				.getBean(UrlAmigavelFlyweight.class)
				.get(null, _categoria, _enderecoUrlSubCategoria, null);
		
		out.append(urlAmigavel.getUrlFinal());
	}
}