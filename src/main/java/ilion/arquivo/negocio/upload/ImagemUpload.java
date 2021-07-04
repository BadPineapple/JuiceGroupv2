package ilion.arquivo.negocio.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ilion.arquivo.negocio.Arquivo;
import ilion.util.CacheUtil;
import ilion.util.Uteis;

@Service
public class ImagemUpload extends Upload {

	static Logger logger = Logger.getLogger(ImagemUpload.class);

	private ArquivoTipoEnum imagemTipoEnum;

	public ImagemUpload() {
		super();
		imagemTipoEnum = ArquivoTipoEnum.IMAGEM;
	}

	public List<String> validar(Object object) throws Exception {
		Imagem imagem = (Imagem) object;

		List<String> erros = new ArrayList<String>();

		List<MultipartFile> arquivos = imagem.getArquivos();

		if(arquivos == null || arquivos.isEmpty()) {
			erros.add("Nenhum arquivo selecionado.");
		} else {
			for (MultipartFile multipartFile : arquivos) {
				validar(imagem, multipartFile, erros);
			}
		}

		return erros;
	}

	private Boolean ehArquivoEmMassa(String nome) {
		Boolean arquivosEmMassa = nome.toLowerCase().endsWith("zip");

		return arquivosEmMassa;
	}

	protected void validar(Imagem imagem, MultipartFile multipartFile, List<String> erros) throws Exception {
		String nome = multipartFile.getOriginalFilename();
		InputStream inputStream = multipartFile.getInputStream();
		Long size = multipartFile.getSize();

		Boolean arquivosEmMassa = ehArquivoEmMassa(multipartFile.getOriginalFilename());

		if( ! arquivosEmMassa ) {

			if( ! imagemTipoEnum.ehExtensaoPermitida(nome) ) {

				erros.add("O arquivo selecionado para upload não é uma imagem!");

			} else if( ! redimencionamento.ehImagemValida(inputStream) ) {

				erros.add("Formato de imagem inválido!");

			} else if( ! imagemTipoEnum.ehTamanhoPermitido(size) ) {

				erros.add("Máximo tamanho permitido é "+imagemTipoEnum.getInfoMaxSize()+".");

			}
		} else {

			ArquivoTipoEnum compactadoTipoEnum = ArquivoTipoEnum.COMPACTADO;

			if( ! compactadoTipoEnum.ehExtensaoPermitida(nome) ) {

				erros.add("Formato de arquivo inválido! Deve ser zip.");

			} else {

				if( ! compactadoTipoEnum.ehTamanhoPermitido(size) ) {

					erros.add("Máximo tamanho permitido é "+compactadoTipoEnum.getInfoMaxSize()+".");

				}

			}
		}

		if( Uteis.ehTrue(imagem.getRedimensionar()) ) {
			if( imagem.getLarguraPequena() == null ) {
				erros.add("Largura pequena deve ser definida.");
			}
		}
	}
	
	@Transactional
	public void gravar(Object object) throws Exception {
		Imagem imagem = (Imagem) object;

		List arquivos = imagem.getArquivos();

		for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
			MultipartFile multipartFile = (MultipartFile) iterator.next();

			Boolean arquivosEmMassa = ehArquivoEmMassa(multipartFile.getOriginalFilename());

			if( arquivosEmMassa ) {

				incluirArquivoEmMassa(imagem, multipartFile);

			} else {

				incluirImagemBanco(imagem, multipartFile.getOriginalFilename(), multipartFile.getInputStream());

			}
		}
		
		CacheUtil.getInstance().resetAllCaches();
		
	}

	private void incluirImagemBanco(Imagem imagem, String nome, InputStream inputStream) throws Exception {
		try {
			
			Imagem imagemParaBanco = gravar(imagem, nome, inputStream);
				
			Integer posicao = arquivoNegocio.proximaPosicao(imagem.getNomeClasse(), imagem.getIdObjeto(), imagem.getCodigo());
			imagemParaBanco.setPosicao(posicao);

			Arquivo arquivoBanco = imagemParaBanco.toArquivo();

			setarTipoDeRegistro(arquivoBanco);

			hibernateUtil.save(arquivoBanco);

		} catch (Exception e) {
			String m = "erro na inclusão do arquivo: "+nome;
			logger.error(m, e);
		}
	}

	private void setarTipoDeRegistro(Arquivo arquivo) {
		if( Uteis.ehNuloOuVazio(arquivo.getArquivo2()) ) {
			arquivo.setTipo(new Byte("1"));
		} else {
			arquivo.setTipo(new Byte("2"));
		}
	}

	protected Imagem gravar(Imagem imagem, String nome, InputStream inputStream) throws Exception {
		Imagem clone = imagem.clone();

		String nomeArquivo1 = arquivoUteis.renomear(nome);
		clone.setArquivo1(nomeArquivo1);

		if( ArquivoTipoEnum.IMAGEM.ehGif(nomeArquivo1) ) {

			clone.setRedimensionar(false);

			clone.setImagemAmpliada(false);

		}

		gravarImagemGrande(clone, inputStream);

		gravarImagemPequena(clone, inputStream);

		obterDimensoesDaImagemPequena(clone);

		return clone;
	}

	private void obterDimensoesDaImagemPequena(Imagem clone) throws Exception {
		File f = new File(imagemTipoEnum.getPathFisico()+clone.getArquivo1());

		Map<String, Integer> dimen = redimencionamento.obterDimensoes(f);

		clone.setLargura(dimen.get("largura"));
		clone.setAltura(dimen.get("altura"));
	}

	private void gravarImagemPequena(Imagem clone, InputStream inputStream) throws Exception {

		if( Uteis.ehTrue(clone.getImagemAmpliada()) ) {

			String pathImagemAmpliada = imagemTipoEnum.getPathFisico()+clone.getArquivo2();

			inputStream = new FileInputStream(new File(pathImagemAmpliada));

		}

		if( Uteis.ehTrue(clone.getRedimensionar()) ) {

			redimencionamento.reduzirGravar(inputStream, imagemTipoEnum.getPathFisico(), clone.getArquivo1(), clone.getLarguraPequena());

		} else {

			arquivoUteis.gravarArquivo(inputStream, clone.getArquivo1(), imagemTipoEnum.getPathFisico());

		}
	}

	private void gravarImagemGrande(Imagem clone, InputStream inputStream) throws Exception {

		if( Uteis.ehTrue(clone.getImagemAmpliada()) ) {

			String nomeArquivo2 = arquivoUteis.renomearImagem_g(clone.getArquivo1());
			clone.setArquivo2(nomeArquivo2);

			if( clone.getLarguraGrande() == null ) {

				arquivoUteis.gravarArquivo(inputStream, nomeArquivo2, imagemTipoEnum.getPathFisico());

			} else {

				redimencionamento.adequarFotoAmpliadaGravar(inputStream, imagemTipoEnum.getPathFisico(), nomeArquivo2, clone.getLarguraGrande());

			}

		}

	}

	private void incluirArquivoEmMassa(Imagem imagem, MultipartFile multipartFile) throws Exception {

//		String nomeArquivoZip = arquivoUteis.renomear(multipartFile.getOriginalFilename());
//		nomeArquivoZip = "em-massa-" + nomeArquivoZip;
//
//		String pathDownloads = imagemTipoEnum.getPathFisico()+"/downloads/";
//
//		arquivoUteis.gravarArquivo(multipartFile.getInputStream(), nomeArquivoZip, pathDownloads);
//
//		File arquivoZip = new File(pathDownloads+nomeArquivoZip);
//
//		if(arquivoZip.exists()) {
//			String pathEmMassa = pathDownloads + "/arquivos-em-massa/";
//			File pasta = new File(pathEmMassa);
//
//			if( ! pasta.exists() ) {
//				pasta.mkdirs();
//			}
//
//			arquivoUteis.descompactarZip(pathDownloads, nomeArquivoZip, pathEmMassa, false);
//
//			if( pasta.exists() ) {
//
//				File[] arquivos = pasta.listFiles();
//
//				if(arquivos != null && arquivos.length != 0) {
//
//					for (int i = 0; i < arquivos.length; i++) {
//						File f = arquivos[i];
//
//						if( ! imagemTipoEnum.ehExtensaoPermitida(f.getName()) ) {
//							continue;
//						}
//
//						incluirImagemBanco(imagem, f.getName(), new FileInputStream(f));
//					}
//
//					excluirArquivoZipEDescompactados(arquivoZip, arquivos);
//
//					System.gc();
//				}
//			}
//		}
	}

//	private void excluirArquivoZipEDescompactados(File arquivoZip, File[] arquivos) {
//		try {
//			arquivoZip.delete();
//		} catch (Exception e) {
//			logger.error(e.getMessage()+", ArquivoEmMassa: falha ao excluir: "+arquivoZip.getName());
//		}
//
//		for (int i = 0; i < arquivos.length; i++) {
//			File f = arquivos[i];
//			try {
//				f.delete();
//			} catch (Exception e) {
//				logger.error(e.getMessage()+", ArquivoEmMassa: falha ao excluir: "+f.getName());
//			}
//		}
//	}
}