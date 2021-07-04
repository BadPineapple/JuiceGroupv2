package ilion.arquivo.taglibs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ArquivoNegocio.Layout;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;

public class UteisTag {

	private static Logger logger = Logger.getLogger(UteisTag.class);

	public static void arquivoTopo(String conteudoInfo, String cssClass, Appendable out) throws IOException {
		Boolean possuiTopos = 
			(Boolean) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_TOPO, Boolean.class);
		if(possuiTopos == null || !possuiTopos)
			return;
		
		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		String codArquivo = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_CODARQUIVO, String.class);
		
		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, codArquivo, Layout.TOPO, false);
		
		if(arquivos == null || arquivos.isEmpty()) {
			return;
		}
		
		if(arquivos.isEmpty())
			return;
		
		UteisTag.setarImagemVideoPreview(arquivos);
		
		imprimindoArquivoTopo(arquivos, cssClass, out);
	}
	
	public static void arquivoLateral(String conteudoInfo, String cssClass, Appendable out) throws IOException {
		Boolean possuiLaterais = 
			(Boolean) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_LATERAL, Boolean.class);
		if(possuiLaterais == null || !possuiLaterais)
			return;

		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		String codArquivo = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_CODARQUIVO, String.class);

		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, codArquivo, Layout.LATERAL, false);

		if(arquivos == null || arquivos.isEmpty()) {
			return;
		}

		if(arquivos.isEmpty())
			return;

		UteisTag.setarImagemVideoPreview(arquivos);
		
		String alinhamento = 
			(String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_LATERAL_ALINHAMENTO, String.class);

		if(alinhamento == null || 
				alinhamento.length() == 0 ||
				"null".equals(alinhamento))
			alinhamento = "right";

		imprimindoArquivoLateral(arquivos, alinhamento, cssClass, out);
	}
	
	public static void imprimindoArquivoTopo(List arquivos, String cssClass, Appendable out) throws IOException {

		out.append("<div class=\"img-center\">");
		
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			UteisTag.imprimirArquivo(arquivo, 
					"fotoTopo", 
					cssClass, 
					out);
		}
		
		out.append("</div>");
	}
	
	public static void imprimindoArquivoLateral(List arquivos, String alinhamento, String cssClass, Appendable out) throws IOException {
		
		String clazz = "left".equals(alinhamento) ? "img-left" : "img-right";
		
		out.append("<div class=\"").append(clazz).append("\">");
		
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();
			
			UteisTag.imprimirArquivo(arquivo, 
					null, 
					cssClass, 
					out);
		}
		
		out.append("</div>");
	}

	public static void arquivoInferior(String conteudoInfo, String cssClass, Boolean downloadsComoLista, Integer largura, Appendable out) {
		Boolean possuiInferiores = 
			(Boolean) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_INFERIOR, Boolean.class);
		if(possuiInferiores == null || !possuiInferiores)
			return;

		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		String codArquivo = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_CODARQUIVO, String.class);

		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, codArquivo, Layout.INFERIOR, false);

		if(arquivos == null || arquivos.isEmpty()) {
			return;
		}
		
		UteisTag.setarImagemVideoPreview(arquivos);

		try {
			if(downloadsComoLista) {
				List<Arquivo> downloads = new ArrayList<Arquivo>();
				for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
					Arquivo arquivo = (Arquivo) iterator.next();

					if(arquivo.getTipo().equals(new Byte("3"))) {
						downloads.add(arquivo);
						iterator.remove();
					}
				}

				downloadsComoListaArquivoInferior(downloads, out);
			}

			if( ! arquivos.isEmpty()) {
				if(largura == null || largura.intValue() == 0) {
					imprimirArquivoInferior(arquivos, cssClass, out);
				} else {
					divsArquivoInferiorComLargura(arquivos, cssClass, largura, out);
				}

				imprimirVideoInferior(arquivos, out);
			}
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private static void downloadsComoListaArquivoInferior(List arquivos, Appendable out) throws IOException {
		out.append("<!-- inicio downloads lista -->");
		out.append("<div class=\"downloadlistacontainer\">");

		UteisTag.imprimirDownloadItensLista(arquivos, out);

		out.append("</div>");
		out.append("<!-- fim downloads lista -->");
	}

	@SuppressWarnings("unchecked")
	public static void imprimirArquivoInferior(List arquivos, String cssClass, Appendable out) throws IOException {
		out.append("<!-- inicio foto inferior -->");
		out.append("<div class=\"containerfotoinferior\">");

		for(Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			if(arquivo.getTipo() != 7) {
				UteisTag.imprimirArquivo(arquivo, 
						"fotoinferior", 
						cssClass, 
						out);
			}
		}

		out.append("</div>");
		out.append("<!-- fim foto inferior -->");
	}

	@SuppressWarnings("unchecked")
	private static void imprimirVideoInferior(List arquivos, Appendable out) throws IOException {
		out.append("<!-- inicio video -->");

		for(Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			if(arquivo.getTipo() == 7) {
				UteisTag.imprimirVideo(arquivo, out);
			}
		}

		out.append("<!-- fim video -->");
	}

	private static void imprimirArquivo(Arquivo arquivo, 
			String cssClassAlinhamentoFoto,
			String cssClass, 
			Appendable out) throws IOException {
		
		UteisTag.imprimir(arquivo, cssClass, out);
		
//		out.append("	<div class=\"").append(cssClassAlinhamentoFoto).append("\" style=\"width:").append(largura.toString()).append("px;\">");
//
//		if(arquivo.getCreditos() != null && arquivo.getCreditos().length() != 0)
//			out.append("	<div class=\"fotocreditos\" style=\"width:").append(largura.toString()).append("px;\">").append(arquivo.getCreditos()).append("</div>");
//
//		UteisTag.imprimir(arquivo, cssClass, out);
//
//		if(arquivo.getTexto() != null && arquivo.getTexto().length() != 0)
//			out.append("		<div class=\"fotolegenda\">").append(arquivo.getTexto()).append("</div>");
//
//		out.append("	</div>");
	}

	private static void imprimirDownloadItensLista(List arquivos, Appendable out) throws IOException {
		for (Iterator iterator = arquivos.iterator(); iterator.hasNext();) {
			Arquivo arquivo = (Arquivo) iterator.next();

			out.append("<ul><li> ");

			if(arquivo.getTexto() != null && arquivo.getTexto().length() != 0) {
				imprimirDownloadItemLista(arquivo, arquivo.getTexto(), out);
			} else {
				imprimirDownloadItemLista(arquivo, arquivo.getArquivo1(), out);
			}

			out.append("</li></ul>");
		}
	}

	private static void imprimirDownloadItemLista(Arquivo arquivo, String texto, Appendable out) throws IOException {
		String pathDownload = ArquivoTipoEnum.DOWNLOAD.getPathRelativo()+arquivo.getArquivo1();
		
		out.append("<a href=\"").append(pathDownload).append("\">").append(texto).append("</a>");
	}

//	private static final String pathPastaJwplayer = ConstantesProp.ENDERECO_ARQUIVOS+"/ilionnet/design/video/";
//	
//	private static void imprimirVideoSimples(Arquivo arquivo, Appendable out) throws IOException {
//		
//		Integer largura = arquivo.getLargura();
//		if(largura == null) {
//			largura = 441;
//		}
//		
//		Integer altura = arquivo.getAltura();
//		if(altura == null) {
//			altura = 226;
//		}
//		
//		String pathVideo = ArquivoTipoEnum.VIDEO.getPathRelativo()+arquivo.getArquivo1();
//		
//		if(arquivo.getArquivo1() != null && arquivo.getArquivo1().toLowerCase().endsWith("flv")) {
//			
//			String preview = arquivo.getPreview();
//			if(preview == null) {
//				preview = pathPastaJwplayer+"/preview.jpg";
//			} else {
//				preview = ArquivoTipoEnum.IMAGEM.getPathRelativo()+preview;
//			}
//			
//			out.append("<embed width=\"").append(largura.toString()).append("\" height=\"").append(altura.toString()).append("\"");
//			out.append(" flashvars=\"file=").append(pathVideo).append("&amp;image=").append(preview).append("\"");
//			out.append(" wmode=\"transparent\" allowscriptaccess=\"always\" allowfullscreen=\"true\" ");
//			out.append(" quality=\"high\" bgcolor=\"#FFFFFF\" name=\"ply\" id=\"ply\"");
//			out.append(" style=\"\" src=\"").append(pathPastaJwplayer).append("jwplayer.swf\" type=\"application/x-shockwave-flash\"/> ");
//		} else {
//			out.append("<video width=\"").append(largura.toString()).append("\" height=\"").append(altura.toString()).append("\" preload controls>");
//			out.append("	<source src='").append(pathVideo).append("' />");
//			out.append("</video>");
//		}
//	}
	
	private static void imprimirVideo(Arquivo arquivo, Appendable out) throws IOException {

		//<embed width="441" height="226" 
		//flashvars="file=video.flv&amp;image=flash/preview.jpg" 
		//wmode="transparent" allowscriptaccess="always" allowfullscreen="true" 
		//quality="high" bgcolor="#FFFFFF" name="ply" id="ply" 
		//style="" src="flash/player.swf" type="application/x-shockwave-flash"/>
		
		String arquivo1 = arquivo.getArquivo1();
		
		String pathVideo = ArquivoTipoEnum.VIDEO.getPathRelativo()+arquivo.getArquivo1();
		
		Integer largura = arquivo.getLargura();
		if(largura == null) {
			largura = 441;
		}
		
		Integer altura = arquivo.getAltura();
		if(altura == null) {
			altura = 226;
		}
		
		if(arquivo1 != null && arquivo1.toLowerCase().endsWith("flv")) {
//			out.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' style='padding:15px 0px 15px 0px;'>");
//			out.append("<tr> ");
//			out.append("  <td align='center'>");
//			
//			
//			out.append("<embed width=\"").append(largura.toString()).append("\" height=\"").append(altura.toString()).append("\"");
//			out.append(" flashvars=\"file=").append(pathVideo).append("&amp;image=").append(pathPastaJwplayer).append("/preview.jpg\"");
//			out.append(" wmode=\"transparent\" allowscriptaccess=\"always\" allowfullscreen=\"true\" ");
//			out.append(" quality=\"high\" bgcolor=\"#FFFFFF\" name=\"ply\" id=\"ply\"");
//			out.append(" style=\"\" src=\"").append(pathPastaJwplayer).append("jwplayer.swf\" type=\"application/x-shockwave-flash\"/> ");
//			
//			out.append("</td>");
//			out.append("</tr>");
//			out.append("</table>");
		} else {
			out.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' style='padding:15px 0px 15px 0px;'>");
			out.append("<tr> ");
			out.append("  <td align='center'>");
			
			out.append("<video width=\"").append(largura.toString()).append("\" height=\"").append(altura.toString()).append("\" preload controls>");
			out.append("	<source src='").append(pathVideo).append("' />");
			out.append("</video>");
			
			out.append("</td>");
			out.append("</tr>");
			out.append("<tr> ");
			out.append("  <td align='center' class='text'>&nbsp;</td>");
			out.append("</tr>");
			out.append("<tr> ");
			out.append("  <td align='center' class='text'>Se voc&ecirc; n&atilde;o"); 
			out.append("    estiver visualizando o video, <a href='").append(pathVideo).append("'>clique aqui para baix&aacute;-lo</a></td>");
			out.append("</tr>");
			out.append("</table>");
		}
	}

	public static void imprimir(Arquivo arquivo, String cssClass, Appendable out) throws IOException {
		
		String title = arquivo.getTitle();
		
		if( Uteis.ehNuloOuVazio(title) ) {
			title = arquivo.getTexto();
		}
		
		if( title == null ) {
			title = "";
		}
		
		Byte tipo = arquivo.getTipo();
		
		if( ! Uteis.ehNuloOuVazio(arquivo.getLink()) ) {
			tipo = new Byte((byte)5);
		}
		
		if(tipo == 1) {
			
			String pathRelativoArquivo = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo1();
			
			out.append("<img alt=\"").append(title).append("\" title=\"").append(title).append("\" class=\"").append(cssClass).append("\" src=\"").append(pathRelativoArquivo).append("\" border=\"0\">");
		} else if(tipo == 2) {
			String pathRelativoArquivo1 = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo1();
			String pathRelativoArquivo2 = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo2();
			
			String group = arquivo.getNomeClasse()+arquivo.getIdObjeto();
			
			out.append("<a class=\"fancybox\" rel=\"").append(group).append("\" title=\"").append(title).append("\" href=\"").append(pathRelativoArquivo2).append("\"><img alt=\"").append(title).append("\" src=\"").append(pathRelativoArquivo1).append("\"/></a>");
		} else if(tipo == 3) {
			String imagemDownload = "icon_img_download_56px.gif";
			if(arquivo.getTexto() == null || 
					arquivo.getTexto().length() < 8) {
				imagemDownload = "icon_img_download_56px.gif";
			} else {
				imagemDownload = "icon_img_download_200px.gif";
			}
			
			String pathRelativoImagem = ArquivoTipoEnum.IMAGEM.getPathRelativo()+imagemDownload;
			String pathRelativoDownload = ArquivoTipoEnum.DOWNLOAD.getPathRelativo()+arquivo.getArquivo1();
			
			out.append("<a target=\"_blank\" href=\"").append(pathRelativoDownload).append("\"><img alt=\"").append(title).append("\" title=\"").append(title).append("\" src=\"").append(pathRelativoImagem).append("\" border=\"0\"/></a>");
//		} else if(tipo == 4) {
//			
//			String pathRelativoFlash = ArquivoTipoEnum.FLASH.getPathRelativo()+arquivo.getArquivo1();
//			
//			if(arquivo.getAlturaExpandido() == null || arquivo.getAlturaExpandido() == 0) {
//				out.append("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\"");
//				out.append("codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0\"");
//				out.append("width=\"").append(arquivo.getLargura().toString()).append("\" height=\"").append(arquivo.getAltura().toString()).append("\" hspace=\"0\" vspace=\"0\" align=\"\">");
//				out.append("<param name=\"movie\" value=\"").append(pathRelativoFlash).append("\"/>");
//				out.append("<param name=\"menu\" value=false/>");
//				out.append("<param name=\"quality\" value=\"high\"/>");
//				out.append("<param name=\"wmode\" value=\"opaque\"/>");			
//				out.append("<embed src=\"").append(pathRelativoFlash).append("\"  width=\"").append(arquivo.getLargura().toString()).append("\" height=\"").append(arquivo.getAltura().toString()).append("\" hspace=\"0\" vspace=\"0\" ALIGN=\"\" menu=\"false\" quality=\"high\" bgcolor=#FFFFFF");
//				out.append("type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" wmode=\"opaque\"></embed>");
//				out.append("</object>");
//			} else {
//				out.append("<div style=\"width:").append(arquivo.getLargura().toString()).append("px;height:").append(arquivo.getAltura().toString()).append("px;float:left;clear:both;overflow:hidden;margin:0 0 10px 0;\" "); 
//				out.append("onmouseout=\"this.style.height='").append(arquivo.getAltura().toString()).append("px';\" ");
//				out.append("onmouseover=\"this.style.height='").append(arquivo.getAlturaExpandido().toString()).append("px';\">");
//				out.append("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0\" width=\"").append(arquivo.getLargura().toString()).append("\" height=\"").append(arquivo.getAlturaExpandido().toString()).append("\">");
//				out.append("<param name=\"movie\" value=\"").append(pathRelativoFlash).append("\" />");
//				out.append("<param name=\"quality\" value=\"high\" />");
//				out.append("<param name=\"wmode\" value=\"transparent\" />");
//				out.append("<embed src=\"").append(pathRelativoFlash).append("\" width=\"").append(arquivo.getLargura().toString()).append("\" height=\"").append(arquivo.getAlturaExpandido().toString()).append("\" quality=\"high\" pluginspage=\"http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\" type=\"application/x-shockwave-flash\" wmode=\"transparent\"></embed>");
//				out.append("</object>");
//				out.append("</div>");
//			}
		} else if(tipo == 5) {
			
			String pathRelativoArquivo = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo1();
			
			out.append("<a href=\"").append(arquivo.getLink()).append("\" target=\"").append(arquivo.getTipoLink()).append("\"><img alt=\"").append(title).append("\" title=\"").append(title).append("\" class=\"").append(cssClass).append("\" src=\"").append(pathRelativoArquivo).append("\" border=\"0\"></a>");
			
		} else if(tipo == 7) {
			//UteisTag.imprimirVideoSimples(arquivo, out);
		}
	}

	public static void arquivoLateralTable(String conteudoInfo, String cssClass, Appendable out) throws IOException {
		Boolean possuiLaterais = 
			(Boolean) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_LATERAL, Boolean.class);
		if(possuiLaterais == null || !possuiLaterais)
			return;

		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		String codArquivo = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_CODARQUIVO, String.class);

		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, codArquivo, Layout.LATERAL, false);

		if(arquivos == null || arquivos.isEmpty()) {
			return;
		}

		if(arquivos.isEmpty())
			return;

		UteisTag.setarImagemVideoPreview(arquivos);
		
		String alinhamento = 
			(String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_LATERAL_ALINHAMENTO, String.class);

		if(alinhamento == null || 
				alinhamento.length() == 0 ||
				"null".equals(alinhamento))
			alinhamento = "right";

		out.append("<table border=\"0\" align=\"").append(alinhamento).append("\" cellpadding=\"0\" cellspacing=\"0\">");
		out.append("  <tr>");

		if("right".equals(alinhamento))
			out.append("    <td width=\"10\"><div style=\"width:10px;\"></div></td>");

		out.append("    <td align=\"").append(alinhamento).append("\"> ");

		imprimindoArquivoLateralTable(arquivos, alinhamento, cssClass, out);

		out.append("</td>");

		if("left".equals(alinhamento))
			out.append("    <td width=\"10\"><div style=\"width:10px;\"></div></td>");

		out.append("  </tr>");
		out.append("  <tr>");
		out.append("    <td height=\"10\" colspan=\"2\"><div style=\"height:10px;\"></div></td>");
		out.append("  </tr>");
		out.append("</table>");
	}

	private static void imprimindoArquivoLateralTable(List arquivos, String alinhamento, String cssClass, Appendable out) throws IOException {
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			out.append(		"<table border=\"0\" cellpadding=\"0\" cellspacing=\"4\">");

			if(arquivo.getCreditos() != null && arquivo.getCreditos().length() != 0) {
				out.append("        <tr>");
				out.append("          <td align=\"left\"><span style=\"font-family: Verdana, Arial, Helvetica, sans-serif; color: #666666; font-size: 8px;\">").append(arquivo.getCreditos()).append("</span></td>"); 
				out.append("        </tr>");
			}

			out.append("        <tr>");
			out.append("  <td>");

			UteisTag.imprimir(arquivo, cssClass, out);

			out.append("  </td>");
			out.append("        </tr>");

			if(arquivo.getTexto() != null && arquivo.getTexto().length() != 0) {
				out.append("<tr>");
				out.append("  <td align=\"center\"><table width=\"").append((arquivo.getLargura()-20)+"").append("\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.append("						<tr>");
				out.append("							<td align=\"center\"><font color=\"#666666\" size=\"1\" face=\"Verdana, Arial, Helvetica, sans-serif\">").append(arquivo.getTexto()).append("</font></td>"); 
				out.append("						</tr>");
				out.append("					</table>");
				out.append("</td>");
				out.append("</tr>");
			}

			out.append("</table>");
		}
	}

	public static void arquivoInferiorTable(String conteudoInfo, Integer largura, String cssClass, Appendable out) throws IOException {
		Boolean possuiInferiores = 
			(Boolean) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_INFERIOR, Boolean.class);
		if(possuiInferiores == null || !possuiInferiores)
			return;

		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		String codArquivo = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_CODARQUIVO, String.class);

		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, codArquivo, Layout.INFERIOR, false);

		if(arquivos == null || arquivos.isEmpty()) {
			return;
		}
		
		UteisTag.setarImagemVideoPreview(arquivos);

		out.append("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		out.append("  <tr>");
		out.append("    <td> ");

		imprimindoArquivoInferiorTable(arquivos, largura, cssClass, out);

		out.append("	</td>");
		out.append("  </tr>");
		out.append("</table>");
	}

	private static void imprimindoArquivoInferiorTable(List arquivos, Integer largura, String cssClass, Appendable out) throws IOException {
		int soma = 0;
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			soma += arquivo.getLargura();

			if(soma >= largura) {
				out.append("</td></tr><tr><td>");
				soma = 0;
			}

			out.append(		"<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"7\">");

			if(arquivo.getCreditos() != null && arquivo.getCreditos().length() != 0) {
				out.append("        <tr>");
				out.append("          <td align=\"left\"><span style=\"font-family: Verdana, Arial, Helvetica, sans-serif; color: #666666; font-size: 8px;\">").append(arquivo.getCreditos()).append("</span></td>"); 
				out.append("        </tr>");
			}

			out.append("        <tr>");
			out.append("  <td>");

			UteisTag.imprimir(arquivo, cssClass, out);

			out.append("  </td>");
			out.append("        </tr>");

			if(arquivo.getTexto() != null && arquivo.getTexto().length() != 0) {
				out.append("<tr>");
				out.append("  <td align=\"center\"><table width=\"").append((arquivo.getLargura()-20)+"").append("\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.append("						<tr>");
				out.append("							<td align=\"center\"><font color=\"#666666\" size=\"1\" face=\"Verdana, Arial, Helvetica, sans-serif\">").append(arquivo.getTexto()).append("</font></td>"); 
				out.append("						</tr>");
				out.append("					</table>");
				out.append("</td>");
				out.append("</tr>");
			}

			out.append("      </table>");
		}
	}

	public static void appendLink(String uri, String categoria, String enderecoUrlSubCategoria, String enderecoUrlArtigo, Appendable out) throws IOException {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		out.append(propNegocio.findValueById(PropEnum.URL));
		out.append("/");

		if(uri != null && uri.length() != 0) {
			out.append(uri);
		} else if(categoria != null ||
				enderecoUrlSubCategoria != null ||
				enderecoUrlArtigo != null) {
			out.append(categoria);
			out.append("/");

			if(enderecoUrlSubCategoria != null && enderecoUrlSubCategoria.length() != 0) {
				out.append(enderecoUrlSubCategoria);
				out.append("/");
			}

			if(enderecoUrlArtigo != null && enderecoUrlArtigo.length() != 0) {
				out.append(enderecoUrlArtigo);
				out.append("/");
			}
		}
	}

	private static void divsArquivoInferiorComLargura(List arquivos, String cssClass, Integer largura, Appendable out) throws IOException {
		out.append("<!-- inicio foto inferior -->");
		out.append("<div id=\"containerfotoinferior\">");
		
		Integer soma = 0;
		
		List<Arquivo> arquivosLinha = null;
		for(Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			soma += arquivo.getLargura()+15;
			
			if(soma >= largura) {
				divLinhaArquivoInferior(arquivosLinha, cssClass, out);

				soma = 0;
				arquivosLinha = null;

				soma += arquivo.getLargura()+15;
			}

			if(arquivosLinha == null) {
				arquivosLinha = new ArrayList<Arquivo>();
			}

			arquivosLinha.add(arquivo);
		}

		if(arquivosLinha != null && !arquivosLinha.isEmpty()) {
			divLinhaArquivoInferior(arquivosLinha, cssClass, out);
		}

		out.append("</div>");
		out.append("<!-- fim foto inferior -->");
	}

	private static void divLinhaArquivoInferior(List<Arquivo> arquivos, String cssClass, Appendable out) throws IOException {
		out.append("<div style=\"clear:both;\">");

		for (Arquivo arquivo : arquivos) {
			UteisTag.imprimirArquivo(arquivo, 
					"fotoinferior", 
					cssClass, 
					out);
		}

		out.append("</div>");
	}
	
	public static void setarImagemVideoPreview(List arquivos) {
		String preview = null;
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();
			
			if("preview".equals(arquivo.getLayout())) {
				preview = arquivo.getArquivo1();
				iter.remove();
			}
		}
		
		if(preview != null) {
			for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
				Arquivo arquivo = (Arquivo) iter.next();
				
				if(arquivo.getArquivo1() != null && 
						arquivo.getArquivo1().toLowerCase().endsWith("flv")) {
					arquivo.setPreview(preview);
				}
			}
		}
	}
}