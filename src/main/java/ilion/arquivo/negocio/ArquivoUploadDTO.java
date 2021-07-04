package ilion.arquivo.negocio;

import org.springframework.web.multipart.MultipartFile;

public class ArquivoUploadDTO {

	private MultipartFile arquivo;

	public MultipartFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}


}