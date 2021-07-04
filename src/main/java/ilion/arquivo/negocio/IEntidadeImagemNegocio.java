package ilion.arquivo.negocio;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IEntidadeImagemNegocio {
	
	public void cadastrarImagemDestaque(Class clazz, Long id, MultipartFile imagem) throws Exception;
	
	public String consultarImagemDestaque(Class clazz, Long id);
	
	public void cadastrarImagens(Class clazz, Long id, List<MultipartFile> imagens) throws Exception;
	
	public void cadastrarImagem(Class clazz, Long id, MultipartFile imagem) throws Exception;
	
	public List<String> carregarImagens(Class clazz, Long id);
	
	public void excluirImagens(Class clazz, Long id, List<String> imagens);
	
}