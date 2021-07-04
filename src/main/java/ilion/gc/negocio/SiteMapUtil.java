package ilion.gc.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.util.Uteis;
import ilion.util.persistencia.HibernateUtil;

@Service
public class SiteMapUtil {
	
	static Logger logger = Logger.getLogger(SiteMapUtil.class);

	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;
	
	@Autowired
	private GCSiteNegocio gcSiteNegocio;
	
	@Autowired
	private HibernateUtil hibernateUtil;

	public SiteMapUtil() {
		super();
	}
	
	private class SiteMapUrlBean {
		private String loq;

		public SiteMapUrlBean(String loq) {
			super();

			this.loq = loq;
		}

		public String getLoq() {
			return loq;
		}

		public void setLoq(String loq) {
			this.loq = loq;
		}


	}

	public void verificarCriarSiteMap() {
		new Thread() {
			@Override
			public void run() {
				
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
						c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
					try {
						criarSiteMap();
					} catch (Exception e) {
						logger.error("criacao do sitemap.xml", e);
					}
				}
				
			}
		}.start();
	}
	
	public void criarSiteMap() throws Exception {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		List<SiteMapUrlBean> beans = new ArrayList<SiteMapUrlBean>();
		
		beans.add(new SiteMapUrlBean(propNegocio.findValueById(PropEnum.URL) + "/home/"));
		
		List<CategoriaArtigo> categoriasSiteMap = categoriaArtigoNegocio.listarCategoriasSiteMap();
		
		for (CategoriaArtigo categoria : categoriasSiteMap) {
			beans.add(new SiteMapUrlBean(propNegocio.findValueById(PropEnum.URL) + "/"+categoria+"/"));
			
			List artigosMap = gcSiteNegocio.listarArtigoSiteMap(categoria);
			
			for (Iterator iterator = artigosMap.iterator(); iterator.hasNext();) {
				Map m = (Map) iterator.next();
				
				String enderecoUrl = (String) m.get("enderecoUrl");
				SubCategoriaArtigo subCategoriaArtigo = (SubCategoriaArtigo) m.get("subCategoria");
				
				StringBuilder sb = new StringBuilder();
				sb.append(propNegocio.findValueById(PropEnum.URL)).append("/");
				sb.append(categoria.getSite()).append("/");
				sb.append(categoria.getNome()).append("/");
				
				if(subCategoriaArtigo != null) {
					sb.append(subCategoriaArtigo.getEnderecoUrl()).append("/");
				}
				
				sb.append(enderecoUrl).append("/");
				
				beans.add(new SiteMapUrlBean(sb.toString()));
			}
		}
		
		exportarParaArquivo(beans);
	}
	
	public String gerarPathNomePorData(Date data) {
		
		String arquivo = "/tmp/goodhoot-sitemap-"+Uteis.formatarDataHora(data, "yyyy-MM-dd")+".xml";
		
		return arquivo;
	}

	private void exportarParaArquivo(List<SiteMapUrlBean> beans) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
		sb.append("<urlset xmlns=\"http://www.google.com/schemas/sitemap/0.84\">").append("\n");
		
		for (SiteMapUrlBean bean : beans) {
			sb.append("<url>").append("\n");
			sb.append("<loc>").append(bean.getLoq()).append("</loc>").append("\n");
			//sb.append("<lastmod>").append(bean.getLastmod()).append("</lastmod>").append("\n");
			//sb.append("<changefreq>").append(bean.getChangeFreqEnum()).append("</changefreq>").append("\n");
			sb.append("</url>").append("\n");
		}
		
		sb.append("</urlset>");
		
		ArquivoUteis.escreverParaArquivo(sb.toString(), gerarPathNomePorData(new Date()));
	}
}
