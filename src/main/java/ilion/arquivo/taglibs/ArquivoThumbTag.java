package ilion.arquivo.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.util.Uteis;

public class ArquivoThumbTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ArquivoThumbTag.class);
	
	private Arquivo arquivo;
	
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		if(arquivo == null) {
			return SKIP_BODY;
		}
		
		ArquivoTipoEnum arquivoTipoEnum = ArquivoTipoEnum.fromExtensao(arquivo.getArquivo1());
		
		if(arquivoTipoEnum == null) {
			return SKIP_BODY;
		}
		
		try {
			
			if(ArquivoTipoEnum.IMAGEM.equals(arquivoTipoEnum)) {
				thumbImagem();
			} else if(ArquivoTipoEnum.DOWNLOAD.equals(arquivoTipoEnum) || ArquivoTipoEnum.COMPACTADO.equals(arquivoTipoEnum)) {
				thumbDownload();
			} else if(ArquivoTipoEnum.VIDEO.equals(arquivoTipoEnum)) {
				thumbVideo();
			}
			
		} catch (Exception e) {
			String m = "Erro ao escrever arquivo thumb: "+arquivo.getArquivo1();
			logger.error(m, e);
		}
		
		return SKIP_BODY;
	}
	
	private void thumbImagem() throws Exception {
		String pathImagem = Uteis.ehNuloOuVazio( arquivo.getArquivo2() ) ? arquivo.getArquivo1() : arquivo.getArquivo2();
		
		pathImagem = ArquivoTipoEnum.IMAGEM.getPathRelativo()+pathImagem;
		
		JspWriter out = pageContext.getOut();
		
		out.append("<a rel=\"lytebox[galeriaprinc]\" href='").append(pathImagem).append("'><img src='").append(pathImagem).append("' border='0' width='32'/></a>");
	}
	
	private void thumbDownload() throws Exception {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		String pathDownload = arquivo.getUrl();
		String pathThumb = propNegocio.findValueById(PropEnum.URL)+"/ilionnet/images/icon-thumb-download.gif";
		
		JspWriter out = pageContext.getOut();
		
		out.append("<a href='").append(pathDownload).append("' target='_blank'><img src='").append(pathThumb).append("' border='0' width='32'/></a>");
	}
	
	private void thumbVideo() throws Exception {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		String pathVideo = arquivo.getUrl();
		String pathThumb = propNegocio.findValueById(PropEnum.URL)+"/images/icon-thumb-video.png";
		
		JspWriter out = pageContext.getOut();
		
		out.append("<a href='").append(pathVideo).append("' target='_blank'><images src='").append(pathThumb).append("' border='0' width='32'/></a>");
	}
	
}