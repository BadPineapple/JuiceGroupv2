package ilion.util.lucene;

import java.util.List;

import org.apache.log4j.Logger;

public class LuceneIndexTotal {
	
	static Logger logger = Logger.getLogger(LuceneIndexTotal.class);
	
	private LuceneConfig luceneConfig;
	
	public LuceneIndexTotal(LuceneConfig luceneConfig) {
		super();
		this.luceneConfig = luceneConfig;
	}

	public void executarThread() {
		new Thread() {
			
			@Override
			public void run() {
				
				indexarTodosConteudos();
				
			}
			
		}.start();
	}
	
	private void indexarTodosConteudos() {
		logger.info("Iniciando indexa��o total de "+luceneConfig.getLuceneNegocio().getEntidade()+" ...");
		
		try {
			
			luceneConfig.getLuceneDesindexar().limpar();
			
			Boolean haRegistrosParaConsultar = true;
			
			Integer maxResult = 50;
			Integer firstResult = 0;
			while(haRegistrosParaConsultar) {

				try {
					
					firstResult = consultarIndexar(firstResult, maxResult);
					
					if(firstResult % 500 == 0) {
						System.gc();
					}
					
				} catch (Exception e) {
					haRegistrosParaConsultar = false;
				}
				
			}

		} catch (Throwable t) {
			String m = "Erro ao indexar todo o conteudo no Lucene";
			logger.error(m, t);
		}

		logger.info("Finalizada indexacao total de "+luceneConfig.getLuceneNegocio().getEntidade()+"...");
	}
	
	private Integer consultarIndexar(Integer firstResult, Integer maxResult) throws Exception {
		
		logger.info("Indexando "+firstResult+" a "+(firstResult+maxResult));
		
		List objetos = luceneConfig.getLuceneNegocio().listarTodosParaIndexar(firstResult, maxResult);
		
		luceneConfig.getLuceneIndexar().executar(objetos);
		
		if(objetos != null) {
			if(objetos.size() < maxResult) {
				throw new RuntimeException("N�o h� mais registros para buscar");
			} else {
				firstResult += maxResult;
			}
		}
		
		return firstResult;
	}
}