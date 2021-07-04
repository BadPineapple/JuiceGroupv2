package ilion.arquivo.negocio;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ilion.arquivo.negocio.ArquivoNegocio.Layout;
import ilion.util.persistencia.HibernateUtil;

@Service
public class ArquivoSiteNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	private List<Byte> tiposDeImagens = Arrays.asList(new Byte("1"), new Byte("2"));
	
	private List<Byte> tiposDeDownload = Arrays.asList(new Byte("3"));
	
	@Cacheable("imagens.site")
	public List<Arquivo> listarImagensSite(String nomeClasse, String idObjeto, Layout layout) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);
		
		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.eq("idObjeto", idObjeto));
		
		if(layout != null) {
			dc.add(Restrictions.eq("layout", layout.toString()));
		}
		
		dc.add(Restrictions.in("tipo", tiposDeImagens));
		
		dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));
		
		dc.addOrder(Order.asc("posicao"));
		
		return (List<Arquivo>) hibernateUtil.list(dc);
	}
	
	public List<Arquivo> listarDownloadsSite(String nomeClasse, String idObjeto, Layout layout) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);
		
		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.eq("idObjeto", idObjeto));
		
		if(layout != null) {
			dc.add(Restrictions.eq("layout", layout.toString()));
		}
		
		dc.add(Restrictions.in("tipo", tiposDeDownload));
		
		dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));
		
		dc.addOrder(Order.asc("posicao"));
		
		return (List<Arquivo>) hibernateUtil.list(dc);
	}
	
}