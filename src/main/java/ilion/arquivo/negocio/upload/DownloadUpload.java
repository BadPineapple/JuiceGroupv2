package ilion.arquivo.negocio.upload;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoUteis;

@Service
public class DownloadUpload extends Upload {

	static Logger logger = Logger.getLogger(DownloadUpload.class);

	private ArquivoTipoEnum downloadTipoEnum;

	public DownloadUpload() {
		super();
		downloadTipoEnum = ArquivoTipoEnum.DOWNLOAD;
	}

	@Override
	public List<String> validar(Object object) throws Exception {
		Download download = (Download) object;

		List<String> erros = new ArrayList<String>();

		List arquivos = download.getArquivos();

		if(arquivos == null || arquivos.isEmpty()) {
			erros.add("Nenhum arquivo selecionado.");
		} else {
			for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
				MultipartFile multipartFile = (MultipartFile) iterator.next();

				validar(download, multipartFile, erros);
			}
		}

		return erros;
	}

	protected void validar(Download download, MultipartFile arquivo, List<String> erros) throws Exception {

		if( ! downloadTipoEnum.ehTamanhoPermitido(arquivo.getSize()) ) {
			erros.add("M�ximo tamanho permitido � "+downloadTipoEnum.getInfoMaxSize()+".");
		}

		if( ! downloadTipoEnum.ehExtensaoPermitida(arquivo.getOriginalFilename()) ) {
			erros.add("Extens�o de arquivo n�o permitida.");
		}
	}

	@Override
	@Transactional
	public void gravar(Object object) throws Exception {
		Download download = (Download) object;

		List arquivos = download.getArquivos();

		for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
			MultipartFile multipartFile = (MultipartFile) iterator.next();

			incluirDownloadBanco(download, multipartFile);
		}
	}

	private void incluirDownloadBanco(Download download, MultipartFile multipartFile) throws Exception {

		try {
			String nomeArquivo1 = arquivoUteis.renomear(multipartFile.getOriginalFilename());

			download.setArquivo1(nomeArquivo1);

			ArquivoUteis.verificarPastaExistente(downloadTipoEnum.getPathFisico());

			arquivoUteis.gravarArquivo(multipartFile.getInputStream(), nomeArquivo1, downloadTipoEnum.getPathFisico());

			download.setArquivo2("icon_img_download.gif");

			download.setAltura(new Integer(56));
			download.setLargura(new Integer(56));

			download.proximaPosicao();

			Arquivo arquivoBanco = download.toArquivo();

			arquivoBanco.setTipo(new Byte("3"));

			hibernateUtil.save(arquivoBanco);

		} catch (Exception e) {
			String m = "erro na inclus�o do arquivo: "+multipartFile.getOriginalFilename();
			logger.error(m, e);
			//new EmailErroThread(e, m);
		}
	}
}