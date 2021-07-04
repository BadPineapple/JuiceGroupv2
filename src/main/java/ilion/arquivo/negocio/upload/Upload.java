package ilion.arquivo.negocio.upload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.arquivo.negocio.Redimencionamento;
import ilion.util.persistencia.HibernateUtil;

@Service
public abstract class Upload {
	
	@Autowired
	protected HibernateUtil hibernateUtil;
	
	@Autowired
	protected ArquivoNegocio arquivoNegocio;
	
	@Autowired
	protected ArquivoUteis arquivoUteis;
	
	@Autowired
	protected Redimencionamento redimencionamento;
	
//	public static Upload fromTipo(String tipo) {
//		
//		Upload upload = null;
//		
//		if("imagem".equals(tipo)) {
//			upload = (Upload) SingletonFactory.getInstance().getObject(ImagemUpload.class);
//		} else if("imagemLink".equals(tipo)) {
//			upload = (Upload) SingletonFactory.getInstance().getObject(ImagemLinkUpload.class);
//		} else if("flash".equals(tipo)) {
//			upload = (Upload) SingletonFactory.getInstance().getObject(FlashUpload.class);
//		} else if("download".equals(tipo)) {
//			upload = (Upload) SingletonFactory.getInstance().getObject(DownloadUpload.class);
//		} else if("video".equals(tipo)) {
//			upload = (Upload) SingletonFactory.getInstance().getObject(VideoUpload.class);
//		} else if("marcadagua".equals(tipo)) {
//			//upload = (Upload) SingletonFactory.getInstance().getObject(MarcaDaguaUpload.class);
//		}
//		
//		if(upload == null) {
//			throw new RuntimeException("tipo de opcao: "+tipo+" nao implementada");
//		}
//		
//		return upload;
//	}

	public ArquivoUteis getArquivoUteis() {
		return arquivoUteis;
	}

	public void setArquivoUteis(ArquivoUteis arquivoUteis) {
		this.arquivoUteis = arquivoUteis;
	}

	public Redimencionamento getRedimencionamento() {
		return redimencionamento;
	}

	public void setRedimencionamento(Redimencionamento redimencionamento) {
		this.redimencionamento = redimencionamento;
	}
	
	protected Boolean ehExtensaoValida(List<String> extensoes, String nome) {
		
		for (String ext : extensoes) {
			if(nome.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		
		return false;
	}
	
	public abstract List<String> validar(Object object) throws Exception;
	
	public abstract void gravar(Object object) throws Exception;
}