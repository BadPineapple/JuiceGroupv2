package ilion.gc.taglibs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.GCSiteNegocio;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

@Service
public class ArtigoUteisTag {

	static Logger logger = Logger.getLogger(ArtigoUteisTag.class);

	@Autowired
	private GCSiteNegocio gcSiteNegocio;

//	protected List listarArtigos(String categoria,
//			String subCategoriaUrl,
//			String secoes,
//			String order,
//			Integer qtd,
//			String varRetorno,
//			Boolean primeiraSubCategoria,
//			String colunas) {
//
//		try {
//
//			if(primeiraSubCategoria){
//
//				//Long idSubCat = gcSiteNegocio.listarIdPrimeiraSubCategoriaSite(categoria);
//
//				//consultarCollection(pageContext, idSubCat, false, false, order, qtd, varRetorno, colunas);
//
//			} else {
//				return gcSiteNegocio.listarArtigoSite(categoria,subCategoriaUrl , secoes, order, qtd, colunas);
//			}
//		} catch (Exception e) {
//			logger.error("", e);
//			new EmailErroThread(e, "listagem de artigos: "+categoria);
//		}
//
//		return null;
//	}

	protected void consultarAleatorio(PageContext pageContext,
			String order,
			String categoria,
			String varRetorno) {

		try {

			Artigo artigo = gcSiteNegocio.listarArtigoAleatorio(categoria);
			pageContext.getRequest().setAttribute(varRetorno, artigo);

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	protected ValueList listarArtigosValueList(String categoria,
			String secoes,
			String order,
			String varRetorno,
			Boolean primeiraSubCategoria,
			String colunas,
			ValueListInfo valueListInfo) {

		try {

			if(primeiraSubCategoria){

//				Long idSub = gcSiteNegocio.listarIdPrimeiraSubCategoriaSite(categoria);
//
//				valueList = gcSiteNegocio.listarSubCategoriaValueList(
//						idSub, false,orderBy, "", new ValueListInfo(vlhForm, new Integer(qtd)));
//
//				pageContext.getRequest().setAttribute(varRetorno, valueList);

			} else {
				return gcSiteNegocio.listarArtigoVLHSite(categoria, secoes, order, colunas, valueListInfo);
			}

		} catch (Exception e) {
			logger.error("", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	protected List listarSubCategoriaArtigos(String categoria,
			String subCategoriaUrl,
			String secoes,
			String order,
			Integer qtd,
			String varRetorno,
			String colunas) {

		try {

			return gcSiteNegocio.listarSubCategoriaArtigoSite(categoria, subCategoriaUrl, secoes, order, qtd, colunas);

		} catch (Exception e) {
			logger.error("", e);
		}

		return null;
	}

	protected ValueList listarSubCategoriaArtigosValueList(String categoria,
			String subCategoriaUrl,
			String secoes,
			String order,
			String colunas,
			ValueListInfo valueListInfo) {

		try {

			return gcSiteNegocio.listarSubCategoriaValueListSite(categoria, subCategoriaUrl, order, colunas, valueListInfo);

		} catch (Exception e) {
			logger.error("", e);
		}

		return null;
	}

	protected void consultarValueListMultiCategoria(PageContext pageContext,
			String categoria,
			String order,
			String qtd,
			String varRetorno,
			String colunas) {

		VLHForm vlhForm = VLHForm.getVLHSession("consultarValueListMultiCategoria", (HttpServletRequest) pageContext.getRequest());

		StringTokenizer st = new StringTokenizer(categoria, ";");
		try {

			Collection<String> categorias = new ArrayList<String>();
			while(st.hasMoreElements()){
				String cat = (String) st.nextElement();
				categorias.add(cat);
			}

			ValueList valueList = gcSiteNegocio.listarArtigoVLHSite(categorias, false, order, colunas, new ValueListInfo(vlhForm, new Integer(qtd)));
			pageContext.getRequest().setAttribute(varRetorno, valueList);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	protected void consultarSubCategoriaValueList(PageContext pageContext,
			String subCategoriaUrl,
			String categoria,
			String order,
			String qtd,
			String varRetorno,
			String colunas) {

		VLHForm vlhForm = VLHForm.getVLHSession("consultarSubCategoriaValueList", (HttpServletRequest) pageContext.getRequest());

		try {

			ValueList valueList = gcSiteNegocio.listarSubCategoriaValueListSite(
					categoria,
					subCategoriaUrl,
					false,
					order,
					colunas,
					new ValueListInfo(vlhForm, new Integer(qtd)));

			pageContext.getRequest().setAttribute(varRetorno, valueList);

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	protected void consultarValueList(PageContext pageContext,
			String categoria,
			String order,
			String qtd,
			String varRetorno,
			boolean primeiraSubCategoria) {

		VLHForm vlhForm = VLHForm.getVLHSession("consultarValueList", (HttpServletRequest) pageContext.getRequest());

		try {

			ValueList valueList = null;

			if(primeiraSubCategoria){

//				Long idSub = gcSiteNegocio.listarPrimeiraSubCategoria(categoria);
//
//				valueList = gcSiteNegocio.listarSubCategoriaValueList(
//						idSub, false,orderBy, "", new ValueListInfo(vlhForm, new Integer(qtd)));
//
//				pageContext.getRequest().setAttribute(varRetorno, valueList);

			}else{

				valueList =  gcSiteNegocio.listarVLHSite(categoria, false, order, new ValueListInfo(vlhForm, new Integer(qtd)));
				pageContext.getRequest().setAttribute(varRetorno, valueList);
			}

		} catch (Exception e) {
			logger.error("", e);
		}
	}
}