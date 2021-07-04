package ilion.arquivo.negocio.upload;

import java.util.ArrayList;
import java.util.List;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.Uteis;

public enum ArquivoTipoEnum {

	IMAGEM("/", 5*1024*1024, "jpg", "jpeg", "png", "gif", "bmp"),

	DOWNLOAD("/downloads/", 20*1024*1024, "zip", "tar.gz", "gz", "bz2", "rar", "odt", "ods", "odp", "odb", "odg", "odf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf", "mp3"),

	VIDEO("/downloads/", 20*1024*1024, "mp4", "ogv", "webm"),

	FLASH("/downloads/", 5*1024*1024, "swf"),

	COMPACTADO("/downloads/", 20*1024*1024, "zip");

	private String sufixoPath;
	private long maxSize;
	private List<String> extensoes;

	private ArquivoTipoEnum(String sufixoPath, long maxSize, String ... extensoes) {

		this.sufixoPath = sufixoPath;
		this.maxSize = maxSize;
		this.extensoes = new ArrayList<String>();

		for (String ext : extensoes) {
			this.extensoes.add(ext);
		}
	}


	public String getPathFisico() {
		ArquivoUteis arquivoUteis = SpringApplicationContext.getBean(ArquivoUteis.class);
		return arquivoUteis.getPathArquivos()+sufixoPath;
	}

	public String getPathRelativo() {
		ArquivoUteis arquivoUteis = SpringApplicationContext.getBean(ArquivoUteis.class);
		return arquivoUteis.getEnderecoArquivos()+sufixoPath;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public String getInfoMaxSize() {
		long info = maxSize/(1*1024*1024);

		return info+"MB";
	}

	public List<String> getExtensoes() {
		return extensoes;
	}

	public static ArquivoTipoEnum fromExtensao(String nome) {
		ArquivoTipoEnum tipoEnum = null;

		for (ArquivoTipoEnum arquivoTipoEnum : values()) {
			for (String extensao : arquivoTipoEnum.getExtensoes()) {
				if( nome.toLowerCase().endsWith("."+extensao) ) {
					tipoEnum = arquivoTipoEnum;
				}
			}
		}

		return tipoEnum;
	}

	public Boolean ehGif(String nome) {
		return nome.toLowerCase().endsWith(".gif");
	}

	public Boolean ehExtensaoPermitida(String nome) {

		for (String extensao : this.getExtensoes()) {
			if( nome.toLowerCase().endsWith("."+extensao) ) {
				return true;
			}
		}

		return false;
	}

	public Boolean ehTamanhoPermitido(long tamanho) {

		return tamanho <= this.getMaxSize();
	}

	public static String getExtensao(String nome) {
		String extensao = "";

		for (ArquivoTipoEnum arquivoTipoEnum : values()) {
			for (String ext : arquivoTipoEnum.getExtensoes()) {
				if( nome.toLowerCase().endsWith("."+ext) ) {
					extensao = ext;
					break;
				}
			}

			if( ! Uteis.ehNuloOuVazio(extensao) ) {
				break;
			}
		}

		return extensao;
	}
}