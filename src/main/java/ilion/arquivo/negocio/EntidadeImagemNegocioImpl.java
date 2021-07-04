package ilion.arquivo.negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ilion.admin.negocio.PropNegocio;

@Service
public class EntidadeImagemNegocioImpl implements IEntidadeImagemNegocio {
	
	@Autowired
	private ArquivoUteis arquivoUteis;
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Override
	public void cadastrarImagemDestaque(Class clazz, Long id, MultipartFile imagem) throws Exception {
		
		if( imagem == null || imagem.isEmpty() ) {
			return;
		}

		String extensao = arquivoUteis.obterFormato(imagem.getOriginalFilename());
		
		String nomeArquivo = "_"+id+"-destaque-"+UUID.randomUUID().toString()+"."+extensao;
		
		String path = getPathFisico(clazz, id);
		
		arquivoUteis.gravarArquivo(imagem.getInputStream(), nomeArquivo, path);

	}

	@Override
	public String consultarImagemDestaque(Class clazz, Long id) {
		
		String retorno = propNegocio.getUrlSemImagem();
		
		if( id == null ) {
			return retorno;
		}
		
		String path = getPathFisico(clazz, id);

		File pasta = new File(path);
		
		File[] arquivos = pasta.listFiles();
		
		if( arquivos == null ) {
			return retorno;
		}

		for (File imagem : arquivos) {
			String nome = imagem.getName();

			if( nome.startsWith("_") ) {

				retorno = getPathWeb(clazz, id)+"/"+nome;

			}

		}

		return retorno;
	}

	@Override
	public void cadastrarImagens(Class clazz, Long id, List<MultipartFile> imagens) throws Exception {
		
		if( imagens == null || imagens.isEmpty() ) {
			return;
		}
		
		for (MultipartFile imagem : imagens) {
			
			cadastrarImagem(clazz, id, imagem);
			
		}
		
	}
	
	@Override
	public void cadastrarImagem(Class clazz, Long id, MultipartFile imagem) throws Exception {
		
		if( imagem == null || imagem.isEmpty() ) {
			return;
		}

		String extensao = arquivoUteis.obterFormato(imagem.getOriginalFilename());

		String nomeArquivo = id+"-"+UUID.randomUUID()+"."+extensao;

		String path = getPathFisico(clazz, id);

		arquivoUteis.gravarArquivo(imagem.getInputStream(), nomeArquivo, path);

	}

	@Override
	public List<String> carregarImagens(Class clazz, Long id) {
		
		String path = getPathFisico(clazz, id);
		
		File pasta = new File(path);
		
		String[] imagens = pasta.list();
		
		List<String> imagensRetorno = new ArrayList<String>();
		
		if( imagens == null ) {
			return imagensRetorno;
		}
		
		String pathWeb = getPathWeb(clazz, id);
		
		for (String imagem : imagens) {
			
			if( imagem.startsWith("_") ) {
				continue;
			}
			
			imagensRetorno.add(pathWeb+imagem);
			
		}
		
		return imagensRetorno;
	}

	public String getPathFisico(Class clazz, Long id) {

		String path = arquivoUteis.getPathArquivos()+clazz.getSimpleName().toLowerCase()+"/"+id+"/";

		ArquivoUteis.verificarPastaExistente(path);

		return path;
	}
	
	public String getPathWeb(Class clazz, Long id) {

		String path = arquivoUteis.getEnderecoArquivos()+clazz.getSimpleName().toLowerCase()+"/"+id+"/";
		
		return path;
	}

	@Override
	public void excluirImagens(Class clazz, Long id, List<String> imagens) {
		
		if( imagens == null || imagens.isEmpty() ) {
			return;
		}
		
		for (String pathImagem : imagens) {
			
			try {
				
				String imagem = pathImagem.substring(pathImagem.lastIndexOf("/")+1);
				String pathFisico = getPathFisico(clazz, id);
				
				File f = new File(pathFisico+"/"+imagem);
				f.delete();
				
			} catch (Exception e) {
			}
			
		}
		
	}
}
