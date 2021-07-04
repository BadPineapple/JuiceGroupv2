package ilion.arquivo.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ilion.util.persistencia.HibernateUtil;

@Service
public class ClienteImagemService {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private ArquivoUteis arquivoUteis;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	@Cacheable("arquivos.por.nome.classe")
	public List listarArquivos(String nomeClasse, String order) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);
		
		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		
		dc.addOrder(Order.asc(order));
		
		return hibernateUtil.list(dc);
	}
	
	@Cacheable("cliente.imagem")
	public String consultarPorNomeClasse(String nomeClasse, String imagemDefault) {
		
		String imagemRetorno = imagemDefault;
		
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, "1", null, false);
		
		if(arquivos != null && arquivos.size() != 0) {
			Arquivo arquivo = (Arquivo) arquivos.get(0);
			
			imagemRetorno = arquivoUteis.getEnderecoArquivos()+arquivo.getArquivo1();
		}
		
		return imagemRetorno;
	}
	
	@CacheEvict(value={"cliente.imagem","arquivos.por.nome.classe"},allEntries=true)
	public void limparCache() {
	}

}