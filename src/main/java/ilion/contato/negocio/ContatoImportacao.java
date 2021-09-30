package ilion.contato.negocio;

import org.springframework.web.multipart.MultipartFile;

public interface ContatoImportacao {
	
	public void importar() throws Exception;
	public String getLog();
	public void importarExcelFuncionario(MultipartFile arquivo, String caminho, String pacote) throws Exception;
}