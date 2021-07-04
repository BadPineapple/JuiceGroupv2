package ilion;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import ilion.admin.negocio.Cidade;
import ilion.admin.negocio.CidadeNegocio;
import ilion.admin.negocio.UF;
import ilion.arquivo.negocio.ArquivoUteis;

@Component
public class TesteUtil {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private CidadeNegocio cidadeNegocio;
	
	public String lerArquivo(String arquivo) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:"+arquivo);
		
		return ArquivoUteis.lerInputStream(resource.getInputStream());
	}
	
	public Cidade cadastrarCidade() {
		
		Cidade cidade = new Cidade();
		cidade.setId(System.currentTimeMillis());
		cidade.setNome("Cidade "+UUID.randomUUID().toString().substring(0, 10));
		cidade.setUf(UF.GO);
		
		cidade = cidadeNegocio.inserir(cidade);
		
		return cidade;
	}
}