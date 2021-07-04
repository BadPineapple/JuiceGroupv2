package ilion.arquivo.negocio.upload;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ilion.arquivo.negocio.Arquivo;

@Service
public class FlashUpload extends Upload {
	
	static Logger logger = Logger.getLogger(FlashUpload.class);
	
	private ArquivoTipoEnum flashTipoEnum;
	
	public FlashUpload() {
		super();
		flashTipoEnum = ArquivoTipoEnum.FLASH;
	}

	@Override
	public List<String> validar(Object object) throws Exception {
		Flash flash = (Flash) object;
		
		List<String> erros = new ArrayList<String>();
		
		List arquivos = flash.getArquivos();
		
		if(arquivos == null || arquivos.isEmpty()) {
			erros.add("Nenhum arquivo selecionado.");
		} else {
			for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
				MultipartFile arquivo = (MultipartFile) iterator.next();
				
				validar(flash, arquivo, erros);
			}
		}
		
		return erros;
	}

	protected void validar(Flash flash, MultipartFile arquivo, List<String> erros) throws Exception {
		
		if( ! flashTipoEnum.ehTamanhoPermitido(arquivo.getSize()) ) {
			erros.add("M�ximo tamanho permitido � "+flashTipoEnum.getInfoMaxSize()+".");
		}
		
		if( ! flashTipoEnum.ehExtensaoPermitida(arquivo.getOriginalFilename()) ) {
			erros.add("Extens�o de arquivo n�o permitida.");
		}
		
		if( flash.getLargura() == null ) {
			erros.add("Largura n�o pode ser vazia.");
		}
		
		if( flash.getAltura() == null ) {
			erros.add("Altura n�o pode ser vazia.");
		}
	}

	@Override
	public void gravar(Object object) throws Exception {
		Flash flash = (Flash) object;

		List arquivos = flash.getArquivos();

		for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
			MultipartFile arquivo = (MultipartFile) iterator.next();

			incluirFlashBanco(flash, arquivo);
		}
	}
	
	private void incluirFlashBanco(Flash flash, MultipartFile arquivo) throws Exception {
		
		try {
			String nomeArquivo1 = arquivoUteis.renomear(arquivo.getOriginalFilename());
			
			flash.setArquivo1(nomeArquivo1);
			
			arquivoUteis.gravarArquivo(arquivo.getInputStream(), nomeArquivo1, flashTipoEnum.getPathFisico());
			
			flash.proximaPosicao();
			
			Arquivo arquivoBanco = flash.toArquivo();
			
			arquivoBanco.setTipo(new Byte("4"));
			
			hibernateUtil.save(arquivoBanco);
			
		} catch (Exception e) {
			String m = "erro na inclusao do arquivo: "+arquivo.getOriginalFilename();
			logger.error(m, e);
		}
	}
}