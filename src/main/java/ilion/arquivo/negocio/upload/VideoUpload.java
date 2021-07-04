package ilion.arquivo.negocio.upload;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ilion.arquivo.negocio.Arquivo;

@Service
public class VideoUpload extends Upload {
	
	static Logger logger = Logger.getLogger(VideoUpload.class);
	
	private ArquivoTipoEnum videoTipoEnum;
		
	public VideoUpload() {
		super();
		videoTipoEnum = ArquivoTipoEnum.VIDEO;
	}

	@Override
	public List<String> validar(Object object) throws Exception {
		Video video = (Video) object;
		
		List<String> erros = new ArrayList<String>();
		
		List<MultipartFile> arquivos = video.getArquivos();
		
		if(arquivos == null || arquivos.isEmpty()) {
			erros.add("Nenhum arquivo selecionado.");
		} else {
			for (MultipartFile multipartFile : arquivos) {
				validar(video, multipartFile, erros);
			}
		}
		
		return erros;
	}

	protected void validar(Video video, MultipartFile multipartFile, List<String> erros) throws Exception {
		String nome = multipartFile.getOriginalFilename();
		Long size = multipartFile.getSize();
		
		if( ! videoTipoEnum.ehTamanhoPermitido(size) ) {
			erros.add("M�ximo tamanho permitido � 5 MB.");
		}
		
		if( ! videoTipoEnum.ehExtensaoPermitida(nome) ) {
			erros.add("Extens�o de arquivo n�o permitida.");
		}
		
		if( video.getLargura() == null ) {
			erros.add("Largura n�o pode ser vazia.");
		}
		
		if( video.getAltura() == null ) {
			erros.add("Altura n�o pode ser vazia.");
		}
	}

	@Override
	@Transactional
	public void gravar(Object object) throws Exception {
		Video video = (Video) object;

		List<MultipartFile> arquivos = video.getArquivos();

		for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
			MultipartFile multipartFile = (MultipartFile) iterator.next();

			incluirVideoBanco(video, multipartFile);
		}
	}
	
	private void incluirVideoBanco(Video video, MultipartFile multipartFile) throws Exception {
		
		try {
			String nomeArquivo1 = arquivoUteis.renomear(multipartFile.getOriginalFilename());
			
			video.setArquivo1(nomeArquivo1);
			
			arquivoUteis.gravarArquivo(multipartFile.getInputStream(), nomeArquivo1, videoTipoEnum.getPathFisico());
			
			video.proximaPosicao();
			
			Arquivo arquivoBanco = video.toArquivo();
			
			arquivoBanco.setTipo(new Byte("7"));
			
			hibernateUtil.save(arquivoBanco);
			
		} catch (Exception e) {
			String m = "erro na inclusao do arquivo: "+multipartFile.getOriginalFilename();
			logger.error(m, e);
		}
	}	
}