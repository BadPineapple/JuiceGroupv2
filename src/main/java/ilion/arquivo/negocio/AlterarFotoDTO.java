package ilion.arquivo.negocio;

import org.springframework.web.multipart.MultipartFile;

public class AlterarFotoDTO {

	private MultipartFile foto;

	public MultipartFile getFoto() {
		return foto;
	}

	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}

}